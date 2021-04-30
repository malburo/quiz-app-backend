package com.example.Quiz.JWT;

import com.example.Quiz.Quick_Pojo_Class.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * This class will extend Spring’s AuthenticationEntryPoint class and override its method commence.
 * It rejects every unauthenticated request and send error code 401
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -9216874552312077958L;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        final String expiredMsg = (String) request.getAttribute("expired");
        System.out.println(expiredMsg);
        final String msg = (expiredMsg != null) ? expiredMsg : "Unauthorized";
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, msg);
        // notify client of response body content type
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        // set the response status code
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // set up the response body
        ErrorMessage errorMessage = new ErrorMessage("401", msg);
        // write the response body
        objectMapper.writeValue(response.getOutputStream(), errorMessage);
        // commit the response
        response.flushBuffer();
    }
}
