package com.ManagmentTask.Task.Controller;


import com.ManagmentTask.Task.Entity.TaskEntity;
import com.ManagmentTask.Task.Service.TaskServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.List;


@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
   private TaskServiceInterface taskServiceInterface;

    @PostMapping("/create")
    public ResponseEntity<String> createtask( @RequestBody TaskEntity taskEntity)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
          return new ResponseEntity<>(taskServiceInterface.generateTask(userName,taskEntity), HttpStatus.CREATED);
          // The UserName is from the manager that manager could create the task.
    }
    @GetMapping("/createdTaskByManager")
    // list of a task that is created by the manager.
    public ResponseEntity<List<TaskEntity>> createdTaskByManager()
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
          return new ResponseEntity<>(taskServiceInterface.returnAllTask(userName),HttpStatus.OK);
    }
    // Employee to all the task that is created.

    @GetMapping("/checkTask")
    public ResponseEntity<List<TaskEntity>> checkTask()
    {
        // All the tasks that are given to the particular employee or person
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        return new ResponseEntity<>(taskServiceInterface.returnTask(userName),HttpStatus.OK);
    }

    // The Manager used this to assign the task to the specific employee

//    @GetMapping("asignedTask/{taskId}")
//    public ResponseEntity<String> assignedTask(@PathVariable ("taskId")long taskId)
//    {
//        // Asigned the particular task to the particular employee
//        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
//        String userName=authentication.getName();
//        return new ResponseEntity<>(taskServiceInterface.assignedTask(userName,taskId),HttpStatus.OK);
//    }

//    // employee update the task
//    @PutMapping("updateStatus/{taskId}")
//    public ResponseEntity<String>updateStatus(@PathVariable ("taskId") long taskId,
//                                              @RequestBody String status)
//    {
//        // update the status of a task that assigned.
//        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
//        String userName=authentication.getName();
//        return  new ResponseEntity<>(taskServiceInterface.updateStatus(taskId,userName,status),HttpStatus.OK);
//    }

}
