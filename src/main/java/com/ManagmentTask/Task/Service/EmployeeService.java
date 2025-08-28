package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.DTOModel.EmployeeRequestModel;
import com.ManagmentTask.Task.DTOModel.EmployeeResponseModel;
import com.ManagmentTask.Task.Entity.EmployeeEntity;
import com.ManagmentTask.Task.Exception.DublicateUser;
import com.ManagmentTask.Task.Exception.EmployeeNotFound;
import com.ManagmentTask.Task.Respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeServiceInterface{

    private final EmployeeRepository employeeRepository;
    private final  PasswordEncoder passwordEncoder;
    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public String createEmpoyee(EmployeeRequestModel employeeRequestModel) {
        Optional<EmployeeEntity> employeeEntityOptional =employeeRepository.findByUserName(employeeRequestModel.getUserName());
        if(employeeEntityOptional.isPresent())throw new DublicateUser("UserName already in use");
        else{
            EmployeeEntity employeeEntity=new EmployeeEntity();
            employeeEntity.setRoles(employeeRequestModel.getRole());
            employeeEntity.setUserName(employeeRequestModel.getUserName());
            employeeEntity.setEmployeeName(employeeRequestModel.getEmployeeName());
            employeeEntity.setPassword(passwordEncoder.encode(employeeRequestModel.getPassword()));
            employeeRepository.save(employeeEntity);
            return  "User is Created";

        }


    }
    @Override
    public  String deleteByUserName(String userName) {
       Optional<EmployeeEntity> employeeEntity=employeeRepository.findByUserName(userName);
        if(employeeEntity.isPresent()) {
            employeeRepository.deleteById(employeeEntity.get().getEmployeeId());
            return "employee profile is deleted";
        }throw new EmployeeNotFound("There is no employee with the id : "+userName);
    }

    @Override
    public EmployeeResponseModel updateEmployee(String userName, EmployeeRequestModel employeeRequestModel) {
        Optional<EmployeeEntity> employeeEntityOptional =employeeRepository.findByUserName(userName);
        if(employeeEntityOptional.isPresent())
        {
              EmployeeEntity employeeEntityDb = employeeEntityOptional.get();
              if(employeeRepository.findByUserName(employeeRequestModel.getUserName()).isPresent())throw new DublicateUser("UserName is already taken");
              else{
                  employeeEntityDb.setUserName(employeeRequestModel.getUserName());
              }
              if(employeeEntityDb.getEmployeeName()!=null)
              {
                  employeeEntityDb.setEmployeeName(employeeRequestModel.getEmployeeName());
              }
              if(employeeEntityDb.getPassword()!=null)
              {
                  employeeEntityDb.setPassword(passwordEncoder.encode(employeeRequestModel.getPassword()));
              }
              employeeRepository.save(employeeEntityDb);
              return EmployeeResponseModel.builder()
                      .employeeId(employeeEntityDb.getEmployeeId())
                      .role(employeeEntityDb.getRoles())
                      .Employeename(employeeEntityDb.getEmployeeName())
                      .userName(employeeEntityDb.getUserName())
                      .assignedTo(List.of())
                      .assignedTo(List.of()).build();
        }
        throw new EmployeeNotFound("There is no employee with the userName : "+userName);
    }
}
