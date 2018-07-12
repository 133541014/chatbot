package com.chao.chatbot.auth.server.controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * @author:WangYichao
 * @Description:
 * @Date:Created in 2018/7/12 22:51
 */
public class UserController{

    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }
}
