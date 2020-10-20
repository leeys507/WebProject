package com.wp.service.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        boolean user=false;
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("USER".equals(auth.getAuthority())){
                user = true;
            }System.out.println(auth.getAuthority());
        }
        if(user) {
            out.println("<script>alert('로그인 성공!');location.href='/studentInfo/studentInfo'</script>");
        }
        else{
            out.println("<script>alert('처음 로그인하셔서 정보입력 페이지로 이동합니다!');location.href='/studentInfo/studentRegistration'</script>");
        }
    }



}
