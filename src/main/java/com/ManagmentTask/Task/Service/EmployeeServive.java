package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.Entity.EmployeeEntity;
import com.ManagmentTask.Task.Exception.DublicateUser;
import com.ManagmentTask.Task.Exception.EmployeeNotFound;
import com.ManagmentTask.Task.Respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServive implements EmployeeServiceInterface{
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeServive(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public EmployeeEntity createEmpoyee(EmployeeEntity employeeEntity) {
        Optional<EmployeeEntity>employeeEntityDb=employeeRepository.findByUserName(employeeEntity.getUserName());
        if(employeeEntityDb.isPresent())throw new DublicateUser("UserName already in use");
        else{
            passwordEncoder.encode(employeeEntity.getPassword());
            return employeeRepository.save(employeeEntity);
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
    public  String updateEmployee(String userName, EmployeeEntity employeeEntity) {
        Optional<EmployeeEntity> employeeEntityOptional =employeeRepository.findByUserName(userName);
        if(employeeEntityOptional.isPresent())
        {
              EmployeeEntity employeeEntity1= employeeEntityOptional.get();
              if(employeeEntity.getName()!=null)
              {
                  employeeEntity1.setName(employeeEntity.getName());
              }
              if(employeeEntity.getPassword()!=null)
              {
                  employeeEntity1.setPassword(employeeEntity.getPassword());
              }
              employeeRepository.save(employeeEntity1);
              return "Details of the user updated";
        }
        throw new EmployeeNotFound("There is no employee with the userName : "+userName);
    }
}
