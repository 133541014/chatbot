package com.chao.chatbot.back.service;

import com.chao.chatbot.back.bean.AjaxResult;
import com.chao.chatbot.back.model.TUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * Created by wangyichao on 2018/6/26 0026.
 */
public interface ITUserService {

    /**
     * 用户注册
     * @param user 用户信息
     * @return 结果
     */
    AjaxResult register(TUser user);

    /**
     * 激活用户
     * @param id 用户主键
     * @param activeCode 激活码
     * @return 操作结果
     */
    boolean userActive(String id,String activeCode);

    /**
     * 用户登录
     * @param user 用户对象
     * @param session 会话对象
     * @return 结果
     */
    AjaxResult login(TUser user, HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException;

    AjaxResult logout(HttpSession session);

    AjaxResult getUserList(String username,String email);
}
