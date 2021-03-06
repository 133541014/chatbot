package com.chao.chatbot.auth.server.config.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author:WangYichao
 * @Description:
 * @Date:Created in 2018/7/12 22:48
 */
@Service
public class SecurityUserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        if ("admin".equalsIgnoreCase(name)) {
            User user = mockUser();
            return user;
        }
        return null;
    }

    private User mockUser() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("admin"));//用户所拥有的角色信息
        User user = new User("admin","123456",authorities);
        return user;
    }
}
