package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.Entity.EmployeeEntity;
import com.ManagmentTask.Task.Exception.DublicateUser;
import com.ManagmentTask.Task.Exception.EmployeeNotFound;
import com.ManagmentTask.Task.Respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.OptionalInt;

@Service
public class EmployeeServive implements EmployeeServiceInterface{
    @Autowired
    private EmployeeRepository employeeRepository;



    @Override
    public EmployeeEntity createEmpoyee(EmployeeEntity employeeEntity) {
        Optional<EmployeeEntity>employeeEntityDb=employeeRepository.findByUserName(employeeEntity.getUserName());
        if(employeeEntityDb.isPresent())throw new DublicateUser("UserName already in use");
        else{
            return employeeRepository.save(employeeEntity);
        }


    }
    @Override
    public  String deleteById(long id) {
       Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(id);
        if(employeeEntity.isPresent()) {
            employeeRepository.deleteById(id);
            return "employee profile is deleted";
        }throw new EmployeeNotFound("There is no employee with the id : "+id);
    }

    @Override
    public  String updateEmployee(long id, EmployeeEntity employeeEntity) {
        Optional<EmployeeEntity> employeeEntityDb=employeeRepository.findById(id);
        if(employeeEntityDb.isPresent())
        {
              EmployeeEntity employeeEntity1=employeeEntityDb.get();
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
        throw new EmployeeNotFound("There is no employee with the id : "+id);
    }
}
