package com.chao.chatbot.back.service.impl;

import com.chao.chatbot.back.bean.AjaxResult;
import com.chao.chatbot.back.bean.common.CommonCode;
import com.chao.chatbot.back.mapper.TUserMapper;
import com.chao.chatbot.back.model.TUser;
import com.chao.chatbot.back.service.ITUserService;
import com.chao.chatbot.back.task.AsyncTask;
import com.chao.chatbot.back.util.EncryptUtils;
import com.chao.chatbot.back.util.IDUtils;
import com.chao.chatbot.back.util.NetUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;

/**
 * Created by wangyichao on 2018/6/26 0026.
 */
@Service
public class TUserServiceImpl implements ITUserService{

    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private AsyncTask asyncTask;

    @Override
    public AjaxResult register(TUser user) {
        //验证用户信息是否合法
        if(!checkUsername(user.getUsername())){
            return AjaxResult.build(500,"用户名已存在");
        }

        if(!checkEmail(user.getEmail())){
            return AjaxResult.build(500,"邮箱已存在");
        }

        //存入用户信息表
        String md5Password = EncryptUtils.getMd5(user.getPassword());
        user.setPassword(md5Password);
        //生成32位激活码
        String code = IDUtils.uuid();
        user.setCode(code);
        user.setCreated(new Date());
        String id = IDUtils.uuid();
        user.setId(id);
        user.setIsActive(CommonCode.UN_ACTIVE);
        user.setUpdated(new Date());
        //异步发送激活邮件
        asyncTask.sendActiveEmail(id,code,user.getEmail());
        userMapper.insert(user);
        return AjaxResult.ok();
    }

    /**
     * 检查用户名
     * @param username 用户名
     * @return 结果
     */
    private boolean checkUsername(String username){
        List<TUser> list = userMapper.getUserByUsername(username);
        if(CollectionUtils.isEmpty(list)){
            return true;
        }
        return false;
    }

    private boolean checkEmail(String email){
        List<TUser> list = userMapper.getUserByEmail(email);
        if(CollectionUtils.isEmpty(list)){
            return true;
        }
        return false;
    }

    @Override
    public boolean userActive(String id, String activeCode) {
        List<TUser> list = userMapper.getUserByIdAndCode(id, activeCode);
        if(CollectionUtils.isEmpty(list)){
            return false;
        }
        TUser user = list.get(0);
        user.setIsActive(CommonCode.ACTIVE);
        user.setUpdated(new Date());
        userMapper.updateByPrimaryKey(user);
        return true;
    }

    @Override
    public AjaxResult login(TUser user,HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException {
        //判断用户名密码是否存在
        user.setPassword(EncryptUtils.getMd5(user.getPassword()));
        List<TUser> list = userMapper.getUserByUsernameAndPassword(user);
        if(CollectionUtils.isEmpty(list)){
            return AjaxResult.build(500,new String("用户名或密码错误".getBytes(),"UTF-8"));
        }
        TUser tUser = list.get(0);
        if(StringUtils.equals(CommonCode.UN_ACTIVE,tUser.getIsActive())){
            return AjaxResult.build(500,new String("用户未激活".getBytes(),"UTF-8"));
        }
        tUser.setOnline(CommonCode.ONLINE);
        tUser.setLoginAddress(user.getLoginAddress());
        session.setAttribute("login-user",tUser);
        userMapper.updateByPrimaryKey(tUser);
        return AjaxResult.ok(tUser);
    }

    @Override
    public AjaxResult logout(HttpSession session) {
        TUser user = (TUser)session.getAttribute("login-user");
        if(user!=null){
            TUser tUser = userMapper.selectByPrimaryKey(user.getId());
            tUser.setOnline(CommonCode.UN_ONLINE);
            tUser.setLoginAddress("");
            userMapper.updateByPrimaryKey(tUser);
        }
        session.invalidate();
        return AjaxResult.ok();
    }

    @Override
    public AjaxResult getUserList(String username, String email) {
        List<TUser> list = userMapper.getUserByParam(username, email);
        return AjaxResult.ok(list);
    }
}
