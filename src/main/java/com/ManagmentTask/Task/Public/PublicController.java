package com.ManagmentTask.Task.Public;

import com.ManagmentTask.Task.DTOModel.EmployeeRequestModel;
import com.ManagmentTask.Task.Entity.EmployeeEntity;
import com.ManagmentTask.Task.Exception.NotDetermineRole;
import com.ManagmentTask.Task.Service.EmployeeServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    private final EmployeeServiceInterface employeeServiceInterface;
    
    public PublicController(EmployeeServiceInterface employeeServiceInterface)
    {
        this.employeeServiceInterface=employeeServiceInterface;
    }
    
    @PostMapping("/createEmployee")
    public ResponseEntity<String>createEmployee(@RequestBody EmployeeRequestModel employeeRequestModel)
    {

        return new ResponseEntity<>(employeeServiceInterface.createEmpoyee(employeeRequestModel), HttpStatus.CREATED);


    }
//    @PostMapping("/createManager")
//    public ResponseEntity<String>createManager(@RequestBody EmployeeRequestModel employeeRequestModel)
//    {
//        if(employeeRequestModel.getRole().equalsIgnoreCase("manager"))
//        {
//            return new ResponseEntity<>(employeeServiceInterface.createEmpoyee(employeeRequestModel),HttpStatus.CREATED);
//        }
//        throw new NotDetermineRole("Role is not Manager");
//    }
    
}
