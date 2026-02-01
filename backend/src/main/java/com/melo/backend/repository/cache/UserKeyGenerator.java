package com.melo.backend.repository.cache;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import com.melo.backend.service.AuthenticationService;

@Component("userKeyGenerator")
public class UserKeyGenerator implements KeyGenerator {

    @Autowired
    private AuthenticationService authService;

    @Override
    public Object generate(Object target, Method method, Object... params) {
        Long userId = authService.getCurrentUser().getId();
        return "user:" + userId;
    }



}
