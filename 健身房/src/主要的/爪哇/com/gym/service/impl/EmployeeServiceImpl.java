package com.gym.service.impl;

import com.gym.dao.EmployeeDao;
import com.gym.pojo.Employee;
import com.gym.pojo.Member;
import com.gym.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeservice")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> findAllEmp() {
        return employeeDao.findAllEmp();
    }

    public int delByEmpId(int empid) {
        return employeeDao.delByEmpId(empid);
    }

    public void addEmp(Employee employee) {
        employeeDao.addEmp(employee);
    }

    public Employee selByEmpid(int empid) {
        return employeeDao.selByEmpid(empid);
    }

    public Boolean updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
        return true;
    }

    public Integer employeeTotal() {
        return employeeDao.employeeTotal();
    }

    public List<Employee> selEmployeeSta(){ return employeeDao.selEmployeeSta(); }

    //员工账号查询
    public List<Employee> selEmpByNumber(int empnumber){ return employeeDao.selEmpByNumber(empnumber); }

    //员工姓名查询
    public List<Employee> selEmpByName(String empname){ return employeeDao.selEmpByName(empname); }

}
