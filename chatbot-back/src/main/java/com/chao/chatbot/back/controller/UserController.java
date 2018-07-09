package com.chao.chatbot.back.controller;

import com.chao.chatbot.back.bean.AjaxResult;
import com.chao.chatbot.back.bean.common.CommonCode;
import com.chao.chatbot.back.model.TUser;
import com.chao.chatbot.back.service.ITUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.UnsupportedEncodingException;

/**
 * Created by wangyichao on 2018/6/26 0026.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ITUserService userService;

    @ApiOperation(value="用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST,produces= MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8;")
    public AjaxResult register(@RequestBody TUser user) {
        return userService.register(user);
    }

    @ApiOperation(value="激活用户")
    @RequestMapping("/active/{id}/{code}")
    public String active(@PathVariable(value = "id", required = true) String id, @PathVariable(value = "code", required = true) String code) {
        boolean result = userService.userActive(id, code);
        if (result) {
            return CommonCode.ACTIVE_SUCCESS;
        } else {
            return CommonCode.ACTIVE_FAILURE;
        }
    }

    @ApiOperation(value="用户登陆")
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces= MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8;")
    public AjaxResult login(@RequestBody TUser user, HttpSession session, HttpServletRequest request){
        try {
            return userService.login(user,session,request);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return AjaxResult.build(500,"服务器错误");
        }
    }

    @ApiOperation(value="用户注销")
    @RequestMapping("/logout")
    public AjaxResult logout(HttpSession session){

        return userService.logout(session);
    }

    /**
     * 条件查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    @ApiOperation(value="条件查询用户信息")
    @RequestMapping(value = "/getUserByParam",method = RequestMethod.POST)
    public AjaxResult getUserByParam(@RequestBody String username,@RequestBody String password){
        return userService.getUserList(username,password);
    }
}
