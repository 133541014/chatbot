package com.chao.chatbot.back.util;

import java.util.UUID;

/**
 * @author:WangYichao
 * @Description:
 * @Date:Created in 2018/5/13 13:07
 */
public class IDUtils {

    /**
     * 生成32位不重复uuid
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
