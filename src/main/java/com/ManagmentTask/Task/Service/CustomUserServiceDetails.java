package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.Configuration.CustomUserDetails;
import com.ManagmentTask.Task.Entity.EmployeeEntity;
import com.ManagmentTask.Task.Respository.EmployeeRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CustomUserServiceDetails implements UserDetailsService {
    private final EmployeeRepository employeeRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<EmployeeEntity> employeeEntityOptional= employeeRepository.findByUserName(username);
        if(employeeEntityOptional.isEmpty())
        {
            throw new UsernameNotFoundException("User does not exits");

        }
       return new CustomUserDetails(employeeEntityOptional.get());
    }

}
