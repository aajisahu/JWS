package com.springbootrest.filter;

import com.springbootrest.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                                                throws ServletException, IOException {
                //1. Read Token From Auth Head
                String token= request.getHeader("Authorization");
                if (token!=null){
                    //Do Validateion
                    String username=jwtUtil.getUserName( token);

                    // Username should not be empty, auth-cont must be empty
                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
                    {
                               UserDetails user= userDetailsService.loadUserByUsername(username);

                               //Validate token
                               boolean isValid= jwtUtil.validateToken(token, user.getUsername());
                               if (isValid)
                               {
                                   UsernamePasswordAuthenticationToken authToken= new
                                                                               UsernamePasswordAuthenticationToken
                                                                                       (username, user.getPassword());
                                   authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                                    //Final object stord in SecurityContex with user details (username ,   passwoed)
                                   SecurityContextHolder.getContext().setAuthentication(authToken);

                               }
                       }
        }
        filterChain.doFilter(request, response);

    }
}
