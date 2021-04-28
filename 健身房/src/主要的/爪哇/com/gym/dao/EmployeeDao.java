package com.gym.dao;

import com.gym.pojo.Employee;
import com.gym.pojo.Equipment;
import com.gym.pojo.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao {

    //查询所有员工
    @Select("select * from employee")
    List<Employee> findAllEmp();

    //id删除员工
    @Delete("delete from employee where empid = #{empid}")
    int delByEmpId(int empid);

    //添加新员工
    @Insert("insert into employee (empid,empnumber,emppwd,empname,empgender,empage,entrytime,staff,message) values" +
            " (#{empid},#{empnumber},#{emppwd},#{empname},#{empgender},#{empage},#{entrytime},#{staff},#{message})")
    void addEmp(Employee employee);

    //根据Id修改员工信息

    @Update("update employee set empid = #{empid},empnumber = #{empnumber}, emppwd = #{emppwd},empname=#{empname},empgender=#{empgender},empage=#{empage},entrytime=#{entrytime},staff=#{staff}," +
            "message=#{message} where empid=#{empid}")
    Boolean updateEmployee(Employee employee);

    //Id查询
    @Select("select * from employee where empid = #{empid}")
    Employee selByEmpid(int empid);

    //查询员工总记录数
    @Select("select count(*) from employee")
    Integer employeeTotal();

    //查询教练
    @Select("select * from employee where staff = '健身教练'")
    List<Employee> selEmployeeSta();

    //员工号码查询
    @Select("select * from employee where empnumber = #{empnumber}")
    List<Employee> selEmpByNumber(int empnumber);

    //员工姓名查询
    @Select("select * from employee where empname like CONCAT('%',#{empname},'%')")
    List<Employee> selEmpByName(String empname);
}
