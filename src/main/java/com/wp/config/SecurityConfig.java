package com.wp.config;

import com.wp.service.security.AuthProvider;
import com.wp.service.security.CusAccessDeniedHandler;
import com.wp.service.security.LoginFailureHandler;
import com.wp.service.security.LoginSuccessHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthProvider authProvider;
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**","/fonts.font-awesome/**","/plugin/**","/scripts/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/studentInfo/studentRegistration").hasAuthority("GUEST")
                .antMatchers("/board/boardInsert","board/boardUpdate").hasAuthority("USER")
                .anyRequest().permitAll()
                .and() // 로그인 설정
                .formLogin()
                .successHandler(new LoginSuccessHandler())
                .failureHandler(new LoginFailureHandler())
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new CusAccessDeniedHandler())
                .and()
                .authenticationProvider(authProvider)
                .csrf().disable();
    }

}
