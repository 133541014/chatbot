package com.chao.chatbot.auth.server.config.service;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @author:WangYichao
 * @Description:
 * @Date:Created in 2018/7/12 22:48
 */
@Service
public class SecurityClientDetailsServiceImpl implements ClientDetailsService{

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        return null;
    }
}
