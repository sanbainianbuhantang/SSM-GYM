package com.gym.service;

import com.gym.pojo.Member;

import java.util.List;

public interface MemberService {

    //查询会员信息
     List<Member> findAll();
    //新增会员信息
     Boolean saveMember(Member member);
    //查询用户账号密码（登录）
     Member checklogin(Member member);
    //删除会员
    int delMember(Integer mid);

    //根据会员卡账号查询会员
    Member selByMnumber(Integer mnumber);

    //根据手机号查询会员
    Member selByMphone(String mnumber);

    //根据手机号查询会员数量
    Integer selByMphonecount(String mphone);

    //id修改会员信息
    Boolean updateMemberByMnumber(Member member);
    //查询会所总人数
    Integer selTotalCount();

}
