package com.chao.chatbot.back.mapper;

import com.chao.chatbot.back.model.TUserFriend;

public interface TUserFriendMapper {
    int deleteByPrimaryKey(String id);

    int insert(TUserFriend record);

    int insertSelective(TUserFriend record);

    TUserFriend selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TUserFriend record);

    int updateByPrimaryKey(TUserFriend record);
}