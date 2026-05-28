package com.ManagmentTask.Task.Security;

import com.ManagmentTask.Task.Configuration.CustomUserDetails;
import com.ManagmentTask.Task.DTOModel.EmployeeResponseModel;
import com.ManagmentTask.Task.DTOModel.LoginDTO;
import com.ManagmentTask.Task.DTOModel.LoginResponseDTO;
import com.ManagmentTask.Task.Entity.EmployeeEntity;
import com.ManagmentTask.Task.Service.CustomUserServiceDetails;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private  final AuthUtil authUtil;

    public LoginResponseDTO loginUser(LoginDTO loginDTO)
    {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUserName(),loginDTO.getPassword())
        );
        System.out.println(authentication.toString());

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        // Step 3 — get EmployeeEntity from CustomUserDetails
        EmployeeEntity employeeEntity = customUserDetails.getEmployeeEntity();


        String  token =authUtil.generateToken(employeeEntity);

        return new LoginResponseDTO(token,employeeEntity.getEmployeeId());


    }
}
