package com.yacer.unilearn.auth.pojos;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public CustomEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        // Set your custom HTTP status
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        // Customize the response content
        response.setContentType("application/json");
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", HttpStatus.UNAUTHORIZED.value());
        responseBody.put("error", "Unauthorized");
        responseBody.put("message", "Authentication failed: " + authException.getMessage());

        // Write the response
        PrintWriter writer = response.getWriter();
        objectMapper.writeValue(writer, responseBody);
        writer.flush();
    }
}
