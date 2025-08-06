package com.ManagmentTask.Task.Controller;


import com.ManagmentTask.Task.Entity.TaskEntity;
import com.ManagmentTask.Task.Service.TaskServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
   private TaskServiceInterface taskServiceInterface;

    @PostMapping("crate/{id}")
    public ResponseEntity<String> createtask(@PathVariable ("id") long id, @RequestBody TaskEntity taskEntity)
    {
          return new ResponseEntity<>(taskServiceInterface.generateTask(id,taskEntity), HttpStatus.CREATED);
    }
    @GetMapping("createdTaskByManager/{id}")
    public ResponseEntity<List<TaskEntity>> createdTaskByManager(@PathVariable ("id") long id)
    {
          return new ResponseEntity<>(taskServiceInterface.returnAllTask(id),HttpStatus.OK);
    }
    // Employee to all the task that are created .

    @GetMapping("checkTask/{id}")
    public ResponseEntity<List<TaskEntity>> checkTask(@PathVariable("id") long employeeId)
    {
        return new ResponseEntity<>(taskServiceInterface.returnTask(employeeId),HttpStatus.OK);
    }
    // Manager used this to assign the task to the specific employee

    @GetMapping("asignedTask/{id}/{taskId}")
    public ResponseEntity<String> assignedTask(@PathVariable ("id") long employeeId,
                                               @PathVariable ("taskId")long taskId)
    {

        return new ResponseEntity<>(taskServiceInterface.assignedTask(employeeId,taskId),HttpStatus.OK);
    }

    // employee update the task
    @PutMapping("updateStatus/{taskId}/{id}")
    public ResponseEntity<String>updateStatus(@PathVariable ("taskId") long taskId,
                                              @PathVariable("id") long employeeId,
                                              @RequestBody String status)
    {
        return  new ResponseEntity<>(taskServiceInterface.updateStatus(taskId,employeeId,status),HttpStatus.OK);
    }

}
