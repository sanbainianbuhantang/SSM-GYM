package com.gym.controller;


import com.gym.pojo.Admin;
import com.gym.pojo.Employee;
import com.gym.pojo.Equipment;
import com.gym.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/findAdminList")
    public String selAdmin(Model model, HttpSession session,HttpServletRequest request){
        List<Admin> adminList = adminService.findAllAdmin();
        session.setAttribute("adminList",adminList);
        model.addAttribute("adminList",adminList);
        String a= (String) request.getSession().getAttribute("msg");
        model.addAttribute("msg",a);
        return "seladmin";
    }

    @RequestMapping("/deladmin")
    public String deladmin(int adminid){
        adminService.delByAdminId(adminid);
        return "redirect:findAdminList";
    }

    @RequestMapping("/toAddAdmin")
    public String toAddAdmin(){
        return "addadmin";
    }

    //增加管理员
    @RequestMapping("/addAdmin")
    public String addAdmin(Admin admin, HttpServletRequest request){
        List<Admin> list = adminService.selAdminByaccount(admin.getAdminaccount());
        if (list==null||list.size()==0){
            adminService.addAdmin(admin);
            request.getSession().setAttribute("msg","添加成功");
        }else{
            request.getSession().setAttribute("msg","添加失败，该账号已存在");
        }
        return "redirect:findAdminList";
    }

    @RequestMapping("/toUpdateAdmin")
    public String toUpdateAdmin(int adminid,Model model){
        List<Admin> adminList = adminService.selByAdminid(adminid);
        model.addAttribute("adminList",adminList);
        return "updateadmin";
    }

    @RequestMapping("/updateAdmin")
    public String updateAdmin(Admin admin,HttpServletRequest request){
        List<Admin> list = adminService.selAdminByaccount(admin.getAdminaccount());
        if (list==null||list.size()==0){
            adminService.updateAdmin(admin);
            request.getSession().setAttribute("msg","修改成功");
        }else{
            request.getSession().setAttribute("msg","修改失败，该账号已存在");
        }
        return "redirect:findAdminList";
    }
}
