package com.gym.controller;


import com.gym.pojo.Employee;
import com.gym.pojo.Member;
import com.gym.service.EmployeeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/toselemployee")
    public String selEmployee(Model model){
        List<Employee> emplist = employeeService.findAllEmp();
        model.addAttribute("emplist",emplist);
        return "selemployee";
    }

    /*跳转修改*/
    @RequestMapping("/toUpdataEmployee")
    public String toUpdataEmploee(Integer empid,Model model){
        Employee updateemploee = employeeService.selByEmpid(empid);
        System.out.println(empid);
        model.addAttribute("updateemploee", updateemploee);
        return "updateemploee";
    }

    /*修改会员信息*/
    @RequestMapping("/updataEmployee")
    public String updataEmployee(Employee employee, Model model) {
        System.out.println(employee.toString());

        Boolean flagupdate = employeeService.updateEmployee(employee);
        if (flagupdate = true) {
            System.out.println("修改员工信息成功");
        } else {
            System.out.println("修改员工信息失败");
        }
        return "redirect:toselemployee";
    }



    @RequestMapping("/toaddemployee")
    public String toAddEmployee(){
        return "addemployee";
    }

    @RequestMapping("/addemployee")
    public String addEmployee(Employee employee){
        //员工账号随机生成
        Random random = new Random();
        String mnum1 = "0000";
        for (int i = 0;i<5;i++){
            mnum1 += random.nextInt(10);
        }
        Integer empnum = Integer.parseInt(mnum1);
        //获取当前日期
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String NowDay = sdf.format(date);
        employee.setEntrytime(NowDay);
        employee.setEmpnumber(empnum);
        employeeService.addEmp(employee);
        return "redirect:toselemployee";
    }

    @RequestMapping("/todelemployee")
    public String toDelEmployee(Model model){
        List<Employee> emplist = employeeService.findAllEmp();
        model.addAttribute("emplist",emplist);
        return "delemployee";
    }

    @RequestMapping("/delemployee")
    public String delEmployee(int empid){
        employeeService.delByEmpId(empid);
        return "redirect:todelemployee";
    }

    /*跳转条件查询页面*/
    @RequestMapping("/toFindEmployee")
    public String toFindEmployee() {
        return "findemployee";
    }

    //员工账号查询
    @RequestMapping("/selEmpByNumber")
    public String selEmpByNumber(int empnumber, Model model){
        List<Employee> list1= employeeService.selEmpByNumber(empnumber);
        System.out.println(empnumber);
        System.out.println(list1);
        if (list1== null||list1.size()==0) {
            String message = "没有查询到该会员账号";
            model.addAttribute("nomessage",message);
        } else {
            model.addAttribute("emplist", list1);
        }
        return "findemployee";
    }

    //员工名称查询
    @RequestMapping("/selEmpByName")
    public String selEmpByName(String empname, Model model){
        List<Employee> list1= employeeService.selEmpByName(empname);
        System.out.println(list1);
        if (list1== null||list1.size()==0) {
            String message = "没有查询到该会员账号";
            model.addAttribute("nomessage",message);
        } else {
            model.addAttribute("emplist", list1);
        }
        return "findemployee";
    }
}
