package com.chao.chatbot.auth.server.test;

import com.sun.jersey.core.util.Base64;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

/**
 * @author:WangYichao
 * @Description:
 * @Date:Created in 2018/7/14 13:35
 */
public class JwtTest {

    /**
     * 解析JWT信息
     */
    @Test
    public void decodeJwt() {
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MzE1ODk2MzUsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiMmI5MjY0OTktZTc2Ni00YmVmLTkzZmQtMmE4MjdjZTlhYjZmIiwiY2xpZW50X2lkIjoiY2xpZW50Iiwic2NvcGUiOlsiYXBwIl19.TPHmErW8cUgb3DFSYpGScMtyId4Dqd7dnByHoLRvVrzTtnevW1W5Cn3MeItKbtqjEqgU0G87Z6ADKR1KjMtcVGdV5XDeIWGvwbvdS0y1QIS9gzZSC5tSETG1Y7tnURSamsvuuyvAoKN3XoFcpfF9ydE9Hl6pejv8dWUu4SjtSZl2LhHf-8ACwrLuXy-psjJkyUUKgnbNwBE9vRheJg4Su-GhDUzJmwhKTpaDsFqOShaHUNz53HfHmgXg4h0xKEZvUy_PsNY5XzJ_gqGgV2w7CcFcp5tiPp8U1wd3TQEP69r4Z_JXH6-ThyygOSpcm0uElFjAXgSMAS96SulwELx2sw";
        Jwt jwt = JwtHelper.decode(token);
        System.out.println(jwt.toString());
    }

    @Test
    public void getPublicKey(){
        Resource resource = new ClassPathResource("chatbot_key.jks");
        KeyPair keyPair = new KeyStoreKeyFactory(resource, "19941014".toCharArray()).getKeyPair("chatbot_key");
        RSAPublicKey key = (RSAPublicKey)keyPair.getPublic();
        System.out.println(key);
        String verifierKey = "-----BEGIN PUBLIC KEY-----\n" + new String(Base64.encode(key.getEncoded()))
                + "\n-----END PUBLIC KEY-----";
        System.out.println(verifierKey);
    }
}
