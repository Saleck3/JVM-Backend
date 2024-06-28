package com.jvm.lecti.presentation.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.jvm.lecti.domain.entity.User;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class TokenUtil {

   @Value("${jwt.secret}")
   private String secretKey;

   private long accessTokenValidity = 180 * 60 * 1000;

   private final JwtParser jwtParser;

   private final String TOKEN_HEADER = "Authorization";

   private final String TOKEN_PREFIX = "Bearer ";

   public TokenUtil(@Value("${jwt.secret}") String secretKey) {
      this.secretKey = secretKey;
      this.jwtParser = Jwts.parser().setSigningKey(secretKey);
   }

   public String createToken(User user) {
      Map<String, Object> claimsMap = new HashMap<>();
      return createToken(user, claimsMap);
   }

   public String createToken(User user, Map<String, Object> claimsMap) {

      Date tokenCreateTime = new Date();
      Date tokenValidity = new Date(tokenCreateTime.getTime() + accessTokenValidity);
      return Jwts
            .builder()
            .setSubject(user.getEmail())
            .addClaims(claimsMap)
            .setExpiration(tokenValidity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
   }

   private Claims parseJwtClaims(String token) {
      return jwtParser.parseClaimsJws(token).getBody();
   }

   public Claims resolveClaims(HttpServletRequest req) {
      try {
         String token = resolveToken(req);
         if (token != null) {
            return parseJwtClaims(token);
         }
         return null;
      } catch (ExpiredJwtException ex) {
         req.setAttribute("expired", ex.getMessage());
         throw ex;
      } catch (Exception ex) {
         req.setAttribute("invalid", ex.getMessage());
         throw ex;
      }
   }

   public String resolveToken(HttpServletRequest request) {

      String bearerToken = request.getHeader(TOKEN_HEADER);
      if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
         return bearerToken.substring(TOKEN_PREFIX.length());
      }
      return null;
   }

   public boolean validateClaims(Claims claims) throws AuthenticationException {
      try {
         return claims.getExpiration().after(new Date());
      } catch (Exception e) {
         throw e;
      }
   }

}
