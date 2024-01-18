package com.spring.aws.springboot.lambda.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SecurityFilter implements Filter
{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpservletrequest= (HttpServletRequest) servletRequest;
        HttpServletResponse httpservleresponse= (HttpServletResponse) servletResponse;

        String token= httpservletrequest.getHeader("token");

        if(token!=null && token.equals("12345ñ")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            ObjectMapper objectMapper= new ObjectMapper();
            ObjectNode objectNode= objectMapper.createObjectNode();
            objectNode.put("Error","Token invalid");
            httpservleresponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpservleresponse.setContentType("application/json");
            httpservleresponse.getOutputStream().write(objectMapper.writeValueAsBytes(objectNode));
            httpservleresponse.getOutputStream().flush();
        }
    }
}
