package com.gym.dao;

import com.gym.pojo.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员账户
 */
@Repository
public interface AdminDao {

    @Select("select * from admin where adminaccount=#{adminaccount} and adminpwd=#{adminpwd}")
    public Admin selByAccAndPwd(Admin admin);

    //查询所有管理员
    @Select("select * from admin")
    List<Admin> findAllAdmin();

    //Id查询
    @Select("select * from admin where adminid = #{adminid}")
    List<Admin> selByAdminid(int adminid);

    //添加新管理员
    @Insert("insert into admin (adminid,adminaccount,adminpwd) values (#{adminid},#{adminaccount},#{adminpwd})")
    Boolean addAdmin(Admin admin);

    //id删除管理员
    @Delete("delete from admin where adminid = #{adminid}")
    int delByAdminId(int adminid);

    //根据Id修改员工信息

    @Update("update admin set adminid = #{adminid},adminaccount = #{adminaccount}, adminpwd = #{adminpwd} where adminid=#{adminid}")
    Boolean updateAdmin(Admin admin);

    //根据账号查询管理员信息
    @Select("select * from admin where adminaccount = #{adminaccount}")
    List<Admin> selAdminByaccount(String adminaccount);
}
