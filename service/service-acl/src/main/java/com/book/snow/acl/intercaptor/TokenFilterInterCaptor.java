/**
 * Author: AKAYWJ
 * Date:2024/4/5 下午6:34
 */
package com.book.snow.common.intercaptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class TokenFilterInterCaptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {



        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
