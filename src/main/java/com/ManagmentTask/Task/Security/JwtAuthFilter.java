package com.ManagmentTask.Task.Security;

import com.ManagmentTask.Task.Configuration.CustomUserDetails;
import com.ManagmentTask.Task.Entity.EmployeeEntity;
import com.ManagmentTask.Task.Respository.EmployeeRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final EmployeeRepository employeeRepository;
    private final AuthUtil authUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      //  log.info("incoming request {} "+request.getRequestURI());


        final String requestHeaderToken=request.getHeader("Authorization");

        if(requestHeaderToken==null || ! requestHeaderToken.startsWith("Bearer "))
        {
            filterChain.doFilter(request,response);
            return ;
        }

        String token = requestHeaderToken.substring(7);

        String userName= authUtil.getUserNameFromToken(token);

        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
           // EmployeeEntity employeeEntity=employeeRepository.findByUserName(userName).orElseThrow();
            CustomUserDetails customUserDetails=new CustomUserDetails(employeeRepository.findByUserName(userName).orElseThrow());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken

                    =new UsernamePasswordAuthenticationToken(customUserDetails,null,null);

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            System.out.println(usernamePasswordAuthenticationToken.toString());
        }
        filterChain.doFilter(request,response);
    }
}
