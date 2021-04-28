package com.gym.pojo;

import java.io.Serializable;

/**
 * 课程表
 */
public class ClassTable implements Serializable {

    private int classid;
    private String classname;
    private String classbegin;
    private String classtime;
    private String teacher;
    private int classprice;
    private int classnumber;
    private String state;

    @Override
    public String toString() {
        return "ClassTable{" +
                "classid=" + classid +
                ", classname='" + classname + '\'' +
                ", classbegin='" + classbegin + '\'' +
                ", classtime='" + classtime + '\'' +
                ", teacher='" + teacher + '\'' +
                ", classprice=" + classprice +
                ", classnumber=" + classnumber +
                ", state='" + state + '\'' +
                '}';
    }

    public int getClassid() { return classid; }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getClassbegin() {
        return classbegin;
    }

    public void setClassbegin(String classbegin) {
        this.classbegin = classbegin;
    }

    public String getClasstime() {
        return classtime;
    }

    public void setClasstime(String classtime) {
        this.classtime = classtime;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getClassprice() { return classprice; }

    public void setClassprice(int classprice) { this.classprice = classprice; }

    public int getClassnumber() { return classnumber; }

    public void setClassnumber(int classnumber) { this.classnumber = classnumber; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }
}
