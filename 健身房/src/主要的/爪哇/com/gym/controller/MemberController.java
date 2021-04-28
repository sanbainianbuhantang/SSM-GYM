package com.gym.controller;

import com.gym.pojo.Admin;
import com.gym.pojo.Member;
import com.gym.service.*;
import com.sun.org.apache.xpath.internal.objects.XNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 会员web
 */
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;


    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private ClassTableService classTableService;


    /*到memberService去查findAll方法*/
    @RequestMapping("/findAll")
    public String findAll(Model model) {
//        System.out.println("查询用户");
        List<Member> list = memberService.findAll();
        model.addAttribute("list", list);
        return "selmember";
    }


    /*跳转新增页面*/
    @RequestMapping("/toAddMember")
    public String toAddMember() {
        return "addmember";
    }


    /*新增会员*/
    @RequestMapping("/saveMember")
    public String saveMember(Member member,Model model) {
        System.out.println("新增会员信息");
        Member member1 =memberService.selByMphone(member.getMphone());
        System.out.println(member1);
        if (member1!=null){
            model.addAttribute("msg","该手机号已被其他用户使用");
            System.out.println("失败");
            return "addmember";
        }else {
            // 会员账号随机生成
            System.out.println("成功");
            Random random = new Random();
            String mnum1 = "2021";
            for (int i = 0; i < 5; i++) {
                mnum1 += random.nextInt(10);
            }
            Integer mnum = Integer.parseInt(mnum1);
//        System.out.println("新增会员卡号为："+mnum);
            //获取办卡时间
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateNowStr = sdf.format(d);

            //计算会员剩余时长
            Integer buymonth = member.getCardclass()%12;//购买月份数
            Integer buyyear = member.getCardclass()/12;//购买年数
            Calendar after = Calendar.getInstance();
            after.setTime(d);
            after.add(Calendar.YEAR,+buyyear);
            after.add(Calendar.MONTH,+buymonth);
            Date d1= after.getTime();
            String dateAfter = sdf.format(d1);
            member.setExpires(dateAfter);

            String pwd = "123456";

            member.setMpwd(pwd);
            member.setMnumber(mnum);
            member.setCardid(mnum);
            member.setCardtime(dateNowStr);


            Boolean flagAdd = memberService.saveMember(member);

            if (flagAdd == true) {
                System.out.println("新增会员保存成功");
            } else if (flagAdd == false) {
                System.out.println("新增会员失败");
            }
            return "redirect:findAll";
        }
    }

    /*用户登录判断*/
    @RequestMapping("/checkLogin")
    public String checkLogin(Member member, Model model, HttpSession session) {
//        System.out.println("控制层用户登录");
        Member m = memberService.checklogin(member);
        System.out.println(m);
        if (m != null) {
            model.addAttribute("member", m);
            System.out.println("用户已登录");
            session.setAttribute("usermember", m);
            return "membermain";
        }
        model.addAttribute("msg", "您输入的账号或密码有误，请重新输入");
        return "memberlogin1";
    }

    /*删除会员*/
    @RequestMapping("/delmember")
    public String delete(int mid) {
        memberService.delMember(mid);
        return "redirect:toDeLMember";
    }

    /*跳转删除会员页面*/
    @RequestMapping("/toDeLMember")
    public ModelAndView toDeLMember() {
        ModelAndView mav = new ModelAndView();
        List<Member> memberList = memberService.findAll();
        mav.addObject("memberList", memberList);
        mav.setViewName("delmember");
        return mav;
    }


    /*跳转条件查询页面*/
    @RequestMapping("/toSelByMnumber")
    public String toSelByMnumber() {
        return "selmembernumber";
    }

    /*会员卡号查询会员*/
    @RequestMapping("/selByMnumber")
    public String selByMnumber(Model model, Integer mnumber) {
        Member member = memberService.selByMnumber(mnumber);
        if (member != null) {
            model.addAttribute("m", member);
        } else {
            String message = "没有查询到该会员账号";
            model.addAttribute("nomessage", message);
        }
        return "selmembernumber";
    }

    /*手机号查询会员*/
    @RequestMapping("/selByMphone")
    public String selByMphone(Model model, String  mphone) {
        Member member = memberService.selByMphone(mphone);
        if (member != null) {
            model.addAttribute("m", member);
        } else {
            String message = "没有查询到该会员账号";
            model.addAttribute("nomessage", message);
        }
        return "selmembernumber";
    }

    /*跳转会员修改页面*/
    @RequestMapping("/toUpdateMember")
    public String toUpdateMember(Integer mid, Model model) {
        Member updatemember = memberService.selByMnumber(mid);
        System.out.println(updatemember);
        model.addAttribute("upmember", updatemember);
        return "updatemember";
    }

    /*修改会员信息*/
    @RequestMapping("/updateMember")
    public String updateMember(Member member, Model model) throws ParseException {
        Member member1= memberService.selByMphone(member.getMphone());
        Integer m1 =member.getMid();
        Integer m2 =member1.getMid();
        Boolean b = (int)m1!=(int)m2;

        //计算会员剩余时长
        Integer buymonth = member.getCardclass()%12;//购买月份数
        Integer buyyear = member.getCardclass()/12;//购买年数
        Calendar after = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        Date date = sdf.parse(member1.getCardtime());//将formate型转化成Date数据类型

        after.setTime(date);
        after.add(Calendar.YEAR,+buyyear);
        after.add(Calendar.MONTH,+buymonth);
        Date d1= after.getTime();
        String dateAfter = sdf.format(d1);
        member.setExpires(dateAfter);

        if (b){
            model.addAttribute("msg","您输入的手机号已被其他用户使用，请重新输入");
            model.addAttribute("upmember",member);
            return "updatemember";
        }else {
            Boolean flagupdate = memberService.updateMemberByMnumber(member);
            if (flagupdate = true) {
                System.out.println("修改会员信息成功");
            } else {
                System.out.println("修改会员信息失败");
            }
            return "redirect:findAll";
        }
    }


    /*管理员登录*/
    @RequestMapping("/adminlogin")
    public String adminLogin(Admin admin, Model model, HttpSession session) {
        Admin a = adminService.adminLogin(admin);
        if (a != null) {
            //会员人数
            Integer membertotal = memberService.selTotalCount();
            //员工人数
            Integer emptotal = employeeService.employeeTotal();
            //会所总人数
            Integer systotal = membertotal + emptotal;
            //器材数
            Integer equtotal = equipmentService.equtotal();
            //课程数
            Integer classcount = classTableService.ClassCount();
            model.addAttribute("classcount",classcount);
            session.setAttribute("classcount",classcount);
            model.addAttribute("membertotal", membertotal);
            session.setAttribute("membertotal", membertotal);
            model.addAttribute("emptotal", emptotal);
            session.setAttribute("emptotal", emptotal);
            model.addAttribute("systotal", systotal);
            session.setAttribute("systotal", systotal);
            model.addAttribute("equtotal", equtotal);
            session.setAttribute("equtotal", equtotal);

            return "main";
        }
        model.addAttribute("msg", "您输入的账号或密码有误，请重新输入");
        return "login1";
    }

    //回到主页
    @RequestMapping("/remain")
    public String remain(HttpSession session, Model model) {
        Integer emptotal = (Integer) session.getAttribute("emptotal");
        Integer membertotal = (Integer) session.getAttribute("membertotal");
        Integer systotal = (Integer) session.getAttribute("systotal");
        Integer equtotal = (Integer) session.getAttribute("equtotal");
        model.addAttribute("equtotal", equtotal);
        model.addAttribute("emptotal", emptotal);
        model.addAttribute("membertotal", membertotal);
        model.addAttribute("systotal", systotal);
        return "main";
    }
}
