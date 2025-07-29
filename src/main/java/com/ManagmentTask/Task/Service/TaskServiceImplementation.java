package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.Entity.EmployeeEntity;
import com.ManagmentTask.Task.Entity.TaskEntity;
import com.ManagmentTask.Task.Exception.EmployeeNotFound;
import com.ManagmentTask.Task.Exception.MultiTaskAssigned;
import com.ManagmentTask.Task.Exception.TaskNotFound;
import com.ManagmentTask.Task.Respository.EmployeeRepository;
import com.ManagmentTask.Task.Respository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImplementation implements TaskServiceInterface {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public String generateTask(long id, TaskEntity taskEntity) {
        Optional<EmployeeEntity> employeeEntityDb=employeeRepository.findById(id);
         if(employeeEntityDb.isPresent() && employeeEntityDb.get().getRoles().equalsIgnoreCase("manager"))
         {
             taskEntity.setAssignedBy(employeeEntityDb.get());
             taskRepository.save(taskEntity);
             return " Task created successfully by Manager ID: " + id;
         }
         throw  new EmployeeNotFound(" Task creation failed: Employee with ID " + id + " is not a manager or does not exist.");
    }

    @Override
    public List<TaskEntity> returnAllTask(long managerId) {
        Optional<EmployeeEntity>employeeEntityDB=employeeRepository.findById(managerId);
        if(employeeEntityDB.isPresent() && employeeEntityDB.get().getRoles().equalsIgnoreCase("manager"))
        {
            return taskRepository.findByAssignedBy_EmployeeId(managerId);
        }
        throw new EmployeeNotFound("No task are assigned by the manager id :"+managerId);
    }

    @Override
    public List<TaskEntity> returnTask(long employeeId) {
       Optional<EmployeeEntity>employeeEntity=employeeRepository.findById(employeeId);
       if(employeeEntity.isPresent())
       {
           return  taskRepository.findByAssignedTo_EmployeeId(employeeId);
       }throw new EmployeeNotFound("There is no employee with id :"+employeeId);
    }

    @Override
    public String assignedTask(long employeeId, long taskId) {
       Optional<TaskEntity> taskEntityDb=taskRepository.findById(taskId);
       Optional<EmployeeEntity>employeeEntityDb=employeeRepository.findById(employeeId);
        if(taskEntityDb.isPresent())
        {
            if(employeeEntityDb.isPresent()) {
                if (taskEntityDb.get().getAssignedTo() == null) {

                    taskEntityDb.get().setAssignedTo(employeeEntityDb.get());
                   return "Task "+taskId+"is assigned to"+employeeId;

                }
                throw new MultiTaskAssigned("Task is Already assigend");
            }   throw new EmployeeNotFound("There is no employee with id : "+employeeId);
        }throw new TaskNotFound("there is no task with id :" +taskId);

    }

    @Override
    public String updateStatus(long taskId, long employeeId, String status) {

    }
}
