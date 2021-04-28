package com.gym.controller;

import com.gym.pojo.ClassOrder;
import com.gym.pojo.ClassTable;
import com.gym.pojo.Employee;
import com.gym.pojo.Member;
import com.gym.service.ClassOrderService;
import com.gym.service.ClassTableService;
import com.gym.service.EmployeeService;
import com.gym.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Controller
@RequestMapping("/classtable")
public class ClassTableController {

    @Autowired
    private ClassTableService classTableService;

    @Autowired
    private ClassOrderService classOrderService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private EmployeeService employeeService;

    //查询课程列表
    @RequestMapping("/findClassTable")
    public ModelAndView findClass(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        List<ClassTable> classList = classTableService.findClassTable();
        session.setAttribute("classlist",classList);
        mav.addObject("classlist", classList);

        mav.setViewName("selclasstable");
        return mav;
    }


    //前往查询所有课程
    @RequestMapping("/toselclasstable")
    public String toclasstable() {
        return "redirect:findClassTable";
    }

    //删除课程
    @RequestMapping("/delclass")
    public String delclass(Integer classid) {
        classTableService.delClassId(classid);
        classTableService.delByConum(classid);
        return "redirect:todelclass";
    }

    @RequestMapping("/todelclass")
    public ModelAndView todelclass() {
        ModelAndView mav = new ModelAndView();
        List<ClassTable> classList = classTableService.findClassTable();
        mav.addObject("classlist", classList);
        mav.setViewName("delclass");
        return mav;
    }

    //跳转新增课程页面
    @RequestMapping("/toaddclass")
    public String toAddClass(Model model){
        List<Employee> staff = employeeService.selEmployeeSta();
        model.addAttribute("staff",staff);
        return "addclass";
    }

    //新增课程
    @RequestMapping("/addclass")
    public String addClass(ClassTable classTable) {
        classTable.setState("待开始");
        classTableService.saveClass(classTable);
        return "redirect:findClassTable";
    }



    /*跳转修改*/
    @RequestMapping("/toUpdataClass")
    public String toUpdataClass(int classid,Model model){
        ClassTable updateclass = classTableService.selByClassId(classid);
        List<Employee> staff = employeeService.selEmployeeSta();
        model.addAttribute("staff",staff);
        model.addAttribute("updateclass", updateclass);
        return "updateclasstable";

    }

    /*修改课程信息*/
    @RequestMapping("/updataclass")
    public String updataClass(ClassTable classTable, Model model) {
        System.out.println(classTable.toString());
        if (classTable.getTeacher()==null){
            ClassTable classTable1 = classTableService.selByClassId(classTable.getClassid());
            classTable.setTeacher(classTable1.getTeacher());
            classTable.setState(classTable1.getState());
        }
        Boolean flagupdate = classTableService.updateClass(classTable);
        if (flagupdate = true) {
            System.out.println("修改课程信息成功");
        } else {
            System.out.println("修改课程信息失败");
        }
        return "redirect:findClassTable";
    }

    //跳转会员报名情况
    @RequestMapping("/tomemberorder")
    public String tomemberorder(Model model){
        List<ClassTable> classList = classTableService.findClassTable();
        model.addAttribute("classlist",classList);
        return "memberorderclass";
    }

    @RequestMapping("/saveclassorder")
    public String saveClassOrder(int classid, Model model, String mphone, RedirectAttributes attr, HttpServletRequest httpServletRequest) {
        ClassTable classTable = classTableService.selByClassId(classid);
        Member member = memberService.selByMphone(mphone);

        Integer conum = classTable.getClassid();
        String coname = classTable.getClassname();
        String coteacher = classTable.getTeacher();
        String cotime = classTable.getClassbegin();
        String comembername = member.getMname();
        Integer conumber = member.getMnumber();

        String classtime = classTable.getClasstime();

        Integer classnumber = classTable.getClassnumber();

        ClassOrder classOrder = new ClassOrder(conum,coname,coteacher,comembername,conumber,cotime,classtime,classnumber);

        Integer mnumber = member.getMnumber();
        ClassOrder co1 = classOrderService.selMemberByCoid(classid,mnumber);
        if ( co1 == null){
            attr.addAttribute("mphone",mphone);
            classOrderService.saveClassOrder(classOrder);
            System.out.println(classOrder);
            httpServletRequest.getSession().setAttribute("code","");
            return "redirect:tomemberclass";
        }else{
            attr.addAttribute("mphone",mphone);
            System.out.println("失败");
            httpServletRequest.getSession().setAttribute("code","用户已经报名过该课程了");
            return "redirect:tomemberclass";
        }
    }

    //跳转课程
    @RequestMapping("/tomemberclass")
    public String toMyClass(Model model,String mphone,HttpServletRequest httpServletRequest) {
        Member member = memberService.selByMphone(mphone);
        model.addAttribute("member", member);
        int mnumber = member.getMnumber();
        System.out.println("跳转"+mnumber);

        List<ClassOrder> memberClass = classOrderService.myClassOrder(mnumber);
        System.out.println("跳转"+mnumber);

        model.addAttribute("memberClass", memberClass);
        return "memberclass";
    }

    //删除用户课程
    @RequestMapping("delmemberclass")
    public String delmemberclass(int coid, String mphone, Model model, RedirectAttributes attr ,HttpServletRequest httpServletRequest) {
        Member member = memberService.selByMphone(mphone);
        model.addAttribute("member",member);
        List<ClassOrder> memberClass = classOrderService.selmemOrderById(coid);
        model.addAttribute("memberClass", memberClass);
        int a= classOrderService.delByCoid(coid);
        attr.addAttribute("mphone",mphone);
        if (a==0){
            System.out.println("退课失败");
            httpServletRequest.getSession().setAttribute("code","该课程不存在");
            return "redirect:tomemberclass";
        }else {
            System.out.println("退课成功");
            return "redirect:tomemberclass";
        }
    }

    @RequestMapping("/findmemberorder")
    public String findMemberOrder(Integer cnum,Model model){
        List<ClassOrder> list1 = classOrderService.memOrderList(cnum);
        if (list1.size()==0||list1==null){
            ClassTable classTable =classTableService.selByClassId(cnum);
            model.addAttribute("orderlist",classTable);
            return "unfindordermmber";
        }else {
            model.addAttribute("orderlist",list1);
            return "findordermember";
        }
    }

    @RequestMapping("/classbegin")
    public String classbegin(Model model,Integer conum){
        List<ClassOrder> list1 = classOrderService.memOrderList(conum);
        int classbefor = 0;
        for (ClassOrder co:list1) {
            classbefor=co.getClassnumber()-1;
//            System.out.println(classbefor);
        }
//        System.out.println(list1);
        classOrderService.upClassnumber(conum,classbefor);
        String beginmsg ="课程进行中";
        classTableService.upClassstate(conum,beginmsg);
        List<ClassOrder> list2 = classOrderService.memOrderList(conum);
        model.addAttribute("orderlist",list2);
        return "findordermember";
    }
}
