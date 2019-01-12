package com.spaceRangers.config.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RememberMeService implements RememberMeServices {
    @Override
    public Authentication autoLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return null;
    }

    @Override
    public void loginFail(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    }

    @Override
    public void loginSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

    }
}
