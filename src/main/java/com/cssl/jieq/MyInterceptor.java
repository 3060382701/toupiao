package com.cssl.jieq;

import com.cssl.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        System.out.println("11");
        System.out.println(request.getRequestURI());
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.html");
        }
            return true;
        }
    }

