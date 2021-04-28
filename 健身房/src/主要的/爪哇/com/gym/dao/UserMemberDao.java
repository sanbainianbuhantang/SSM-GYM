package com.gym.dao;

import com.gym.pojo.Member;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMemberDao {

  @Select("select * from member where mnumber=#{mnumber}")
    public Member selByNumber(int mnumber);

  @Update("update member set mid = #{mid},mnumber = #{mnumber}, mpwd = #{mpwd},mname=#{mname},mgender=#{mgender},mage=#{mage},mheight=#{mheight},mweight=#{mweight}," +
          "mphone=#{mphone},cardid=#{cardid},cardclass=#{cardclass} where mid=#{mid}")
  Boolean updateUserMember(Member member);
}
