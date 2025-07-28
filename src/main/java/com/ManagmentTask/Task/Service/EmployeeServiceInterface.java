package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.Entity.EmployeeEntity;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeServiceInterface {
   EmployeeEntity createEmpoyee(EmployeeEntity employeeEntity);
   String deleteById(long id);
  String updateEmployee(long id,EmployeeEntity employeeEntity);

}
