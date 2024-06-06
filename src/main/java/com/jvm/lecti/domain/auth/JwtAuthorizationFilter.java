package com.jvm.lecti.domain.auth;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jvm.lecti.presentation.util.TokenUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

   private final TokenUtil tokenUtil;

   private final ObjectMapper mapper;

   public JwtAuthorizationFilter(TokenUtil tokenUtil, ObjectMapper mapper) {
      this.tokenUtil = tokenUtil;
      this.mapper = mapper;
   }

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
         throws ServletException, IOException {
      Map<String, Object> errorDetails = new HashMap<>();

      try {
         String accessToken = tokenUtil.resolveToken(request);
         if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
         }
         Claims claims = tokenUtil.resolveClaims(request);

         if (claims != null & tokenUtil.validateClaims(claims)) {
            String email = claims.getSubject();
            Authentication authentication = new UsernamePasswordAuthenticationToken(email, "", Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
         }

      } catch (Exception e) {
         errorDetails.put("message", "Authentication Error");
         errorDetails.put("details", e.getMessage());
         response.setStatus(HttpStatus.UNAUTHORIZED.value());
         response.setContentType(MediaType.APPLICATION_JSON_VALUE);
         mapper.writeValue(response.getWriter(), errorDetails);

      }
      filterChain.doFilter(request, response);
   }

}
