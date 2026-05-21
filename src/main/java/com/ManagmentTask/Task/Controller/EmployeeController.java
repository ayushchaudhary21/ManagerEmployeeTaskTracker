package com.ManagmentTask.Task.Controller;

import com.ManagmentTask.Task.DTOModel.EmployeeRequestModel;
import com.ManagmentTask.Task.DTOModel.EmployeeResponseModel;
import com.ManagmentTask.Task.Service.EmployeeServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
   private final EmployeeServiceInterface employeeServiceInterface;

    public EmployeeController(EmployeeServiceInterface employeeServiceInterface) {
        this.employeeServiceInterface = employeeServiceInterface;
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEmployee() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        return  new ResponseEntity<>(employeeServiceInterface.deleteByUserName(userName),HttpStatus.OK);

    }
//
//    @PutMapping("/update")
//    public ResponseEntity<EmployeeResponseModel> updateEmployee(@RequestBody EmployeeRequestModel employeeRequestModel)
//    {
//        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
//        String userName=authentication.getName();
//        return new ResponseEntity<>(employeeServiceInterface.updateEmployee(userName,employeeRequestModel),HttpStatus.OK);
//
//    }
}
