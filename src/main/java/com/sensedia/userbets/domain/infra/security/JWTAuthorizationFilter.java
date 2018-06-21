package com.sensedia.userbets.domain.infra.security;

import static com.sensedia.userbets.domain.infra.security.SecurityConstants.HEADER_STRING;
import static com.sensedia.userbets.domain.infra.security.SecurityConstants.SECRET;
import static com.sensedia.userbets.domain.infra.security.SecurityConstants.TOKEN_PREFIX;

import com.sensedia.userbets.domain.User;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  JWTAuthorizationFilter(AuthenticationManager authManager) {
    super(authManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req,
      HttpServletResponse res,
      FilterChain chain) throws IOException, ServletException {
    val header = req.getHeader(HEADER_STRING);

    if (Objects.isNull(header) || !header.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(req, res);
    } else {
      val authentication = getAuthentication(req);

      SecurityContextHolder.getContext().setAuthentication(authentication);
      chain.doFilter(req, res);
    }
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    val token = request.getHeader(HEADER_STRING);
    UsernamePasswordAuthenticationToken ret = null;
    if (Objects.nonNull(token)) {
      val claims = Jwts.parser()
          .setSigningKey(SECRET.getBytes())
          .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
          .getBody();

      if (Objects.nonNull(claims)) {
        ret = new UsernamePasswordAuthenticationToken(
            User.builder().userId(claims.get("userId", String.class))
                .userName(claims.get("userName", String.class)).build(), null, new ArrayList<>());
      }
    }
    return ret;
  }

}
