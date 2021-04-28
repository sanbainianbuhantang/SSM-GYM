package com.gym.service;

import com.gym.pojo.Employee;
import com.gym.pojo.Member;

import java.util.List;

public interface EmployeeService {

    //查询所有员工
    List<Employee> findAllEmp();

    //id删除员工
    int delByEmpId(int empid);

    //添加员工
    void addEmp(Employee employee);

    //根据Id查询
    Employee selByEmpid(int empid);

    //id修改员工信息
    Boolean updateEmployee(Employee employee);

    //查询员工总数
    Integer employeeTotal();

    //查询所有教练
    List<Employee> selEmployeeSta();

    //员工Id查询
    List<Employee> selEmpByNumber(int empnumber);

    //员工姓名模糊查询
    List<Employee> selEmpByName(String empname);
}
