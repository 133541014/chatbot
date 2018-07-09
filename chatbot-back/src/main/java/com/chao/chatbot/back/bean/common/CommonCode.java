package com.chao.chatbot.back.bean.common;

/**
 * 常量定义
 * Created by wangyichao on 2018/6/26 0026.
 */
public interface CommonCode {

    /**
     * 用户激活状态(未激活)
     */
    public static final String UN_ACTIVE = "0";

    /**
     * 用户激活状态(已激活)
     */
    public static final String ACTIVE = "1";

    /**
     * 用户在线状态(不在线)
     */
    public static final String UN_ONLINE= "0";

    /**
     * 用户在线状态(在线)
     */
    public static final String ONLINE = "1";


    /**
     * 激活成功
     */
    public static final String ACTIVE_SUCCESS = "<h1>您已经成功激活，请登录</h1>";

    /**
     * 激活失败
     */
    public static final String ACTIVE_FAILURE = "<h1>激活失败，未知原因</h1>";

}
