package com.example.schedule.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
import java.net.HttpCookie;

@Slf4j
public class ScheduleFilter implements Filter {

    private static final String[] WHITE_LIST = {"user/signup","user/login","user/session-login"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest reqest = (HttpServletRequest) servletRequest;
        String requestURI = reqest.getRequestURI();

        log.info("request URI={}", requestURI);

        log.info("로그인 필터 로직 실행");

        if (!isWhiteList(requestURI)) {
            HttpSession session = reqest.getSession(false);
            if (session == null || session.getAttribute("sessionKey") == null) {
                throw new RuntimeException("로그인 해주세요.");
            }

            //로그인 성공 로직
            log.info("로그인에 성공했습니다.");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
        // simpleMatch : WHILTE_LIST가 일치하는 경우만 TRUE반환
    }


}
