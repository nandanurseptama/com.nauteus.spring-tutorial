package com.nauteus.spring_tutorial.commons.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("request coming: method={},path={},requestId={}",
                request.getMethod(),
                request.getServletPath(),
                request.getHeader("Request-Id"));
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("request ended: method={},path={},requestId={},status={}",
                request.getMethod(),
                request.getServletPath(),
                request.getHeader("Request-Id"),
                response.getStatus());
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
