package com.ManagmentTask.Task.Configuration;

import com.ManagmentTask.Task.Entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

  private final EmployeeEntity employeeEntity;

    public CustomUserDetails(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public String getUsername() {
        return employeeEntity.getUserName();
    }

    @Override
    public String getPassword() {
        return employeeEntity.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
