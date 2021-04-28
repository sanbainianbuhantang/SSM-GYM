package com.gym.pojo;

import java.io.Serializable;

public class ClassOrder implements Serializable {

    private int coid;
    private Integer conum;//课程编号
    private String coname;//课程名称
    private String coteacher;//代课教练
    private String comembername;//会员名称
    private Integer conumber;//会员账号
    private String cotime; //开课时间
    private String classtime; //课程时间
    private int classnumber;//课程剩余次数


    public ClassOrder() {
    }

    public ClassOrder(Integer conum, String coname, String coteacher, String comembername, Integer conumber, String cotime, String classtime, Integer classnumber) {
        this.conum = conum;
        this.coname = coname;
        this.coteacher = coteacher;
        this.comembername = comembername;
        this.conumber = conumber;
        this.cotime = cotime;
        this.classtime=classtime;
        this.classnumber=classnumber;
    }

    @Override
    public String toString() {
        return "ClassOrder{" +
                "coid=" + coid +
                ", coname='" + coname + '\'' +
                ", conum=" + conum +
                ", coteacher='" + coteacher + '\'' +
                ", comembername='" + comembername + '\'' +
                ", conumber=" + conumber +
                ", cotime='" + cotime + '\'' +
                ", classtime='" + classtime + '\'' +
                ", classnumber=" + classnumber +
                '}';
    }

    public int getCoid() {
        return coid;
    }

    public void setCoid(int coid) {
        this.coid = coid;
    }

    public String getConame() {
        return coname;
    }

    public void setConame(String coname) {
        this.coname = coname;
    }

    public Integer getConum() {
        return conum;
    }

    public void setConum(Integer conum) {
        this.conum = conum;
    }

    public String getCoteacher() {
        return coteacher;
    }

    public void setCoteacher(String coteacher) {
        this.coteacher = coteacher;
    }

    public String getComembername() {
        return comembername;
    }

    public void setComembername(String comembername) {
        this.comembername = comembername;
    }

    public Integer getConumber() {
        return conumber;
    }

    public void setConumber(Integer conumber) {
        this.conumber = conumber;
    }

    public String getCotime() {
        return cotime;
    }

    public void setCotime(String cotime) {
        this.cotime = cotime;
    }

    public String getClasstime() { return classtime; }

    public void setClasstime(String classtime) { this.classtime = classtime; }

    public int getClassnumber() {
        return classnumber;
    }

    public void setClassnumber(int classnumber) {
        this.classnumber = classnumber;
    }

}
