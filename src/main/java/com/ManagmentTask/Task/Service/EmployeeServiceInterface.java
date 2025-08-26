package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.DTOModel.EmployeeRequestModel;
import com.ManagmentTask.Task.DTOModel.EmployeeResponseModel;
import com.ManagmentTask.Task.Entity.EmployeeEntity;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeServiceInterface {
     String createEmpoyee(EmployeeRequestModel employeeRequestModel);
   String deleteByUserName(String userName);
EmployeeResponseModel updateEmployee(String userName, EmployeeRequestModel employeeRequestModel);

}
