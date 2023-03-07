package com.cybersoft.osahaneat.security;

import com.cybersoft.osahaneat.utils.JwtUtilHeplers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class CustomFilterJwt extends OncePerRequestFilter {

    @Autowired
    JwtUtilHeplers jwtUtilHeplers;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Hellooo Filter");
        try {
            String jwt = parseJwt(request);
            boolean isSuccess = jwtUtilHeplers.verifyToken(jwt);
            if (isSuccess){
                String data = jwtUtilHeplers.getDataFromToken(jwt);
                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken("", "", new ArrayList<>());
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(token);
            }
            System.out.println("Hello filter:  " + isSuccess);

        } catch (Exception e){
            System.err.println("Lỗi nhận token - " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth)){
            return headerAuth;
        }
        return null;
    }
}
