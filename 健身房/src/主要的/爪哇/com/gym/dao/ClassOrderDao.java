package com.gym.dao;


import com.gym.pojo.ClassOrder;
import com.gym.pojo.ClassTable;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassOrderDao {

    //查询所有报名表信息
    @Select("select * from classorder")
    public List<ClassOrder> findAllClassOrder();

    //添加报名信息
    @Insert("insert into classorder(coid,conum,coname,coteacher,comembername,conumber,cotime,classtime,classnumber) values " +
            "(#{coid},#{conum},#{coname},#{coteacher},#{comembername},#{conumber},#{cotime},#{classtime},#{classnumber})")
    public void saveClassOrder(ClassOrder classOrder);

    //更新课程剩余次数
    @Update("update classorder set classnumber=#{classnumber} where conum=#{conum}")
    Boolean upClassnumber(@Param("conum") Integer conum,
                          @Param("classnumber") int classnumber);

    //id查询个人报名
    @Select("select * from classorder where conumber = #{conumber}")
    List<ClassOrder> myClassOrder(Integer conumber);

    //删除我的课程
    @Delete("delete from classorder where coid = #{coid}")
    int delByCoid(Integer coid);

    //查询会员是否报名该课程
    @Select("select * from classorder where conum = #{conum} and conumber = #{conumber}")
    ClassOrder selMemberByCoid(@Param("conum") Integer conum,
                               @Param("conumber") Integer conumber);

    //查询会员报名情况
    @Select("select * from classorder where conum=#{conum}")
    List<ClassOrder> memOrderList(Integer conum);

    //根据报名表Id查询报名信息
    @Select("select * from classorder where coid=#{coid}")
    List<ClassOrder> selmemOrderById(int coid);

    //
}
