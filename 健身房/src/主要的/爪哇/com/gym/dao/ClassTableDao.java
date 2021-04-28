package com.gym.dao;

import com.gym.pojo.ClassOrder;
import com.gym.pojo.ClassTable;
import com.gym.pojo.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 课程表接口
 */
@Repository
public interface ClassTableDao {

    //查询所有课程
    @Select("select * from classtable")
    public List<ClassTable> findClassTable();

    //删除课程
    @Delete("delete from classtable where classid=#{classid}")
    public int delByClassid(Integer classid);

    //添加课程
    @Insert("insert into classtable(classid,classname,classbegin,classtime,teacher,classprice,classnumber,state) values " +
            "(#{classid},#{classname},#{classbegin},#{classtime},#{teacher},#{classprice},#{classnumber},#{state})")
    public void saveclass(ClassTable classTable);

    //根据id查询课表
    @Select("select * from classtable where classid=#{classid}")
    public ClassTable selByClassId(Integer classid);

    //根据Id修改课程信息
    @Update("update classtable set classid = #{classid},classname = #{classname}, " +
            "classbegin = #{classbegin},classtime=#{classtime},teacher=#{teacher},classprice=#{classprice},classnumber=#{classnumber},state=#{state} where classid=#{classid}")
    Boolean updateClass(ClassTable classTable);


    //CONUM删除课程
    @Delete("delete from classorder where conum = #{conum}")
    Boolean delByConum(Integer conum);


    //查询课程记录总数
    @Select("select count(*) from equipment")
    Integer ClassCount();

    //查询课程状态为准备
    @Select("select * from classtable where state=#{state}")
    public List<ClassTable> selReady(String state);

    //更改课程状态
    @Update("update classtable set state=#{state} where classid=#{classid}")
    Boolean upClassstate(@Param("classid") int classid,
                          @Param("state") String state);
}
