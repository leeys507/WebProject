package com.wp.service.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String targetUrl;
        try{
            targetUrl = savedRequest.getRedirectUrl();
        }catch (NullPointerException e){
            targetUrl = null;
        }
        boolean user=false;
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("USER".equals(auth.getAuthority())){
                user = true;
            }
        }
        if(user) {
            if(targetUrl==null){
                out.println("<script>location.href='/yu/login'</script>");
            }
            else {
                redirectStrategy.sendRedirect(request, response, targetUrl);
            }
        }
        else{
            out.println("<script>alert('처음 로그인하셔서 정보입력페이지로 이동합니다!');location.href='/yu/studentInfo/studentRegistration'</script>");
        }
    }
}
