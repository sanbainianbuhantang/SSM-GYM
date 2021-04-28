package com.gym.controller;


import com.gym.pojo.ClassOrder;
import com.gym.pojo.ClassTable;
import com.gym.pojo.Member;
import com.gym.service.ClassOrderService;
import com.gym.service.ClassTableService;
import com.gym.service.MemberService;
import com.gym.service.UserMemberService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/usermember")
public class UserMemberController {

    @Autowired
    private UserMemberService userMemberService;

    @Autowired
    private ClassTableService classTableService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ClassOrderService classOrderService;


    //跳转个人空间
    @RequestMapping("/tomemberzone")
    public String toMemberZone(Model model,HttpSession session) {
        Member member = (Member) session.getAttribute("usermember");
        model.addAttribute("member", member);
        return "memberzone";
    }

    //跳转报名选课页面
    @RequestMapping("/tomemberapplyclass")
    public String toMemberApplyClass(Model model,String mphone) {
        Member member = memberService.selByMphone(mphone);
        String state = "待开始";
        List<ClassTable> classList = classTableService.selReady(state);
        model.addAttribute("member", member);
        model.addAttribute("classlist", classList);
        return "memberapplyclass";
    }

    //跳转会员主页
    @RequestMapping("/tomembermain")
    public String tomembermain(Model model,HttpSession session) {
        Member member = (Member) session.getAttribute("usermember");
        model.addAttribute("member", member);
        return "membermain";
    }


    //跳转我的课程
    @RequestMapping("/tomyclass")
    public String toMyClass(Model model,HttpSession session) {
        Member member = (Member) session.getAttribute("usermember");
        model.addAttribute("member", member);
        int mnumber = member.getMnumber();
        List<ClassOrder> myClassOrder = classOrderService.myClassOrder(mnumber);
        model.addAttribute("myClassOrder", myClassOrder);
        System.out.println("跳转我的课程");
        return "myclass";
    }

    //跳转修改我的信息
    @RequestMapping("/toUpdateMy")
    public String toUpdateMy(HttpSession session,Model model){
        Member member =  (Member) session.getAttribute("usermember");
        System.out.println(member.toString());
        model.addAttribute("member", member);
        return "updatemyself";
    }

    //修改我的信息
    @RequestMapping("/updateMy")
    public String updateMy(HttpSession session,Member member,Model model){
        Member member1 =  (Member) session.getAttribute("usermember");
        member.setMid(member1.getMid());

        Member member2= memberService.selByMphone(member.getMphone());
        if(member2!=null){
            System.out.println("3"+member2);
            Integer m1 =member1.getMid();
            Integer m2 =member2.getMid();
            Boolean b = (int)m1!=(int)m2;
            if(b){
                model.addAttribute("msg","您输入的手机号已被其他用户使用，请重新输入");
                model.addAttribute("upmember",member);
                return "updatemyself";
            }else {
                member1.setMname(member.getMname());
                member1.setMpwd(member.getMpwd());
                member1.setMheight(member.getMheight());
                member1.setMweight(member.getMweight());
                member1.setMgender(member.getMgender());
                member1.setMage(member.getMage());
                member1.setMphone(member.getMphone());

                member1.setCardclass(member1.getCardclass());

                userMemberService.updateMember(member1);
                return "redirect:tomemberzone";
            }
        }else{
            member1.setMname(member.getMname());
            member1.setMpwd(member.getMpwd());
            member1.setMheight(member.getMheight());
            member1.setMweight(member.getMweight());
            member1.setMgender(member.getMgender());
            member1.setMage(member.getMage());
            member1.setMphone(member.getMphone());

            member1.setCardclass(member1.getCardclass());

            userMemberService.updateMember(member1);
            return "redirect:tomemberzone";
        }
    }

    //用户跳转课程列表
    @RequestMapping("/toselmemberclass")
    public ModelAndView toselmemberclass(HttpSession session,Model  model) {
        ModelAndView mav = new ModelAndView();
        Member member =  (Member) session.getAttribute("usermember");
        model.addAttribute("member", member);

        List<ClassTable> classList = classTableService.findClassTable();
        session.setAttribute("classlist",classList);
        mav.addObject("classlist", classList);

        mav.setViewName("memberselclass");
        return mav;
    }
}

