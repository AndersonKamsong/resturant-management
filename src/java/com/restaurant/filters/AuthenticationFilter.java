///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.restaurant.filters;
//
///**
// *
// * @author anderson
// */
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
////@WebFilter("/*")
//public class AuthenticationFilter implements Filter {
//    private static final List<String> ALLOWED_PATHS = Arrays.asList(
//        "/login", "/register", "/logout", "/css/", "/js/", "/images/"
//    );
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpSession session = httpRequest.getSession(false);
//        
//        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
//        
//        boolean allowedPath = ALLOWED_PATHS.stream().anyMatch(path::startsWith);
//        
//        if (allowedPath || session != null && session.getAttribute("user") != null) {
//            chain.doFilter(request, response);
//        } else {
//            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
//        }
//    }
//    
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {}
//    
//    @Override
//    public void destroy() {}
//}
