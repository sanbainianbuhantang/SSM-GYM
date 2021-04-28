package com.gym.service;


import com.gym.pojo.ClassTable;

import java.util.List;

public interface ClassTableService {

    //查询课程列表
    public List<ClassTable> findClassTable();

    //删除课程
    public int delClassId(Integer classId);

    //新增课程
    public void saveClass(ClassTable classTable);

    //id查询课程
    ClassTable selByClassId(Integer classId);

    //id修改课程信息
    Boolean updateClass(ClassTable classTable);

    Boolean delByConum(Integer conum);

    //查询课程总数
    Integer ClassCount();

    //查询课程状态为准备
    List<ClassTable> selReady(String state);

    //更新课程状态
    Boolean upClassstate(int classid,String state);


}
