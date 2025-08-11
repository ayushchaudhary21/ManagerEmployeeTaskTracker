package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.Entity.EmployeeEntity;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeServiceInterface {
   EmployeeEntity createEmpoyee(EmployeeEntity employeeEntity);
   String deleteByUserName(String userName);
  String updateEmployee(String userName,EmployeeEntity employeeEntity);

}
