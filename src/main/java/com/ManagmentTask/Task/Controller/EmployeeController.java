package com.ManagmentTask.Task.Controller;

import com.ManagmentTask.Task.Entity.EmployeeEntity;
import com.ManagmentTask.Task.Service.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeServiceInterface employeeServiceInterface;

    @PostMapping
    public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeEntity employeeEntity) {
        return new ResponseEntity<>(employeeServiceInterface.createEmpoyee(employeeEntity), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteByID/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") long id) {
        employeeServiceInterface.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") long id,
                                            @RequestBody EmployeeEntity employeeEntity)
    {
        employeeServiceInterface.updateEmployee(id,employeeEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    @PutMapping("updatetask/{task}")
}
