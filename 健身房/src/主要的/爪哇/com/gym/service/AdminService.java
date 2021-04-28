package com.gym.service;

import com.gym.pojo.Admin;

import java.util.List;

public interface AdminService {

    //管理员登录
    public Admin adminLogin(Admin admin);

    //查询所有员工
    List<Admin> findAllAdmin();

    //id删除管理员
    int delByAdminId(int adminid);

    //添加管理员
    Boolean addAdmin(Admin admin);

    //根据Id查询
    List<Admin> selByAdminid(int adminid);

    //id修改管理员信息
    Boolean updateAdmin(Admin admin);

    //账号查询
    List<Admin> selAdminByaccount(String adminaccount);
}
