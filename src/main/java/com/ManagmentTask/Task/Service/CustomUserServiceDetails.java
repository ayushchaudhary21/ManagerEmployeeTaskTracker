package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.Configuration.CustomUserDetails;
import com.ManagmentTask.Task.Entity.EmployeeEntity;
import com.ManagmentTask.Task.Respository.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserServiceDetails implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    public CustomUserServiceDetails(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<EmployeeEntity> employeeEntityOptional= employeeRepository.findByUserName(username);
        if(!employeeEntityOptional.isPresent())
        {
            throw new UsernameNotFoundException("User does not exits");

        }
       return new CustomUserDetails(employeeEntityOptional.get());
    }
}
