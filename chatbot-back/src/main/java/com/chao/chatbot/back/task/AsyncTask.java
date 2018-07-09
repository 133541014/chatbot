package com.chao.chatbot.back.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.net.InetAddress;

/**
 * 异步任务
 * Created by wangyichao on 2018/6/26 0026.
 */
@Component
public class AsyncTask {

    /**
     * 邮件发送对象
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发件人
     */
    @Value("${spring.mail.username}")
    private String sender;

    /**
     * 端口
     */
    @Value("${server.port}")
    private String port;

    /**
     * 发送激活邮件
     * @param id 用户id
     * @param activeCode 激活码
     */
    @Async
    public void sendActiveEmail(String id,String activeCode,String email){
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(email);
            helper.setSubject("Chat Bot聊天网站激活邮件");

            StringBuffer sb = new StringBuffer();
            //获取服务器地址
            InetAddress inetAddress = InetAddress.getLocalHost();
            String host = inetAddress.getHostAddress();

            sb.append("<h2>欢迎注册Chat Bot 在线聊天网站</h2>")
                    .append("<h2><a href='http://"+host+":"+port+"/user/active/"+id+"/"+activeCode+"' style='font-size:20px'>点击链接激活</a><h2>");
            helper.setText(sb.toString(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }
}
