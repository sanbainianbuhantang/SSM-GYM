package com.gym.service.impl;

import com.gym.dao.AdminDao;
import com.gym.pojo.Admin;
import com.gym.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    public Admin adminLogin(Admin admin) {
        Admin a1 = adminDao.selByAccAndPwd(admin);
        return a1;
    }

    public List<Admin> findAllAdmin() {
        return adminDao.findAllAdmin();
    }

    public int delByAdminId(int adminid) {
        return adminDao.delByAdminId(adminid);
    }

    public Boolean addAdmin(Admin admin) {
        return adminDao.addAdmin(admin);
    }

    public List<Admin> selByAdminid(int adminid) {
        return adminDao.selByAdminid(adminid);
    }

    public Boolean updateAdmin(Admin admin) {
        adminDao.updateAdmin(admin);
        return true;
    }

    public List<Admin> selAdminByaccount(String adminaccount){
        return adminDao.selAdminByaccount(adminaccount);
    }
}
