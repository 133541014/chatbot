package com.chao.chatbot.back.mapper;

import com.chao.chatbot.back.model.TUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    @Select("select * from t_user where username = #{username}")
    @ResultMap("BaseResultMap")
    List<TUser> getUserByUsername(String username);

    @Select("select * from t_user where email = #{email}")
    @ResultMap("BaseResultMap")
    List<TUser> getUserByEmail(String email);

    @Select("select * from t_user where id = #{id} and code = #{code}")
    @ResultMap("BaseResultMap")
    List<TUser> getUserByIdAndCode(@Param("id") String id, @Param("code") String code);

    @Select("select * from t_user where username = #{username} and password = #{password}")
    @ResultMap("BaseResultMap")
    List<TUser> getUserByUsernameAndPassword(TUser user);

    List<TUser> getUserByParam(@Param("username")String username,@Param("email")String email);

}