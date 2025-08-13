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
    public String generateTask(String userName, TaskEntity taskEntity) {
        Optional<EmployeeEntity> employeeEntityDb=employeeRepository.findByUserName(userName);
         if(employeeEntityDb.isPresent() && employeeEntityDb.get().getRoles().equalsIgnoreCase("manager"))
         {
             taskEntity.setAssignedBy(employeeEntityDb.get());
             taskRepository.save(taskEntity);
             return " Task created successfully by Manager ID: " + employeeEntityDb.get().getEmployeeId();
         }
         throw  new EmployeeNotFound(" Task creation failed: Employee with ID " + employeeEntityDb.get().getEmployeeId() + " is not a manager or does not exist.");
    }

    @Override
    public List<TaskEntity> returnAllTask(String userName) {
        Optional<EmployeeEntity>employeeEntityDB=employeeRepository.findByUserName(userName);
        if(employeeEntityDB.isPresent() && employeeEntityDB.get().getRoles().equalsIgnoreCase("manager"))
        {
            return taskRepository.findByAssignedBy_EmployeeId(employeeEntityDB.get().getEmployeeId());
        }
        throw new EmployeeNotFound("No task are assigned by the manager userName : "+userName );
    }

    @Override
    public List<TaskEntity> returnTask(String userName) {
       Optional<EmployeeEntity> employeeEntityOptional =employeeRepository.findByUserName(userName);
       if(employeeEntityOptional.isPresent())
       {
           return  taskRepository.findByAssignedTo_EmployeeId(employeeEntityOptional.get().getEmployeeId());
       }throw new EmployeeNotFound("There is no employee with userName :"+userName);
    }

//    @Override
//    public String assignedTask(String userName, long taskId) {
//       Optional<TaskEntity> taskEntityDb=taskRepository.findById(taskId);
//       Optional<EmployeeEntity>employeeEntityDb=employeeRepository.findByUserName(userName);
//        if(taskEntityDb.isPresent())
//        {
//            if(employeeEntityDb.isPresent()) {
//                if (taskEntityDb.get().getAssignedTo() == null) {
//
//                    taskEntityDb.get().setAssignedTo(employeeEntityDb.get());
//                   return "Task " +taskId+ "is assigned to "+ userName;
//
//                }throw new MultiTaskAssigned("Task is Already assigend");
//
//            } throw new EmployeeNotFound("There is no employee with userNamex : "+);
//
//        }throw new TaskNotFound("there is no task with id :" +taskId);
//
//    }

//    @Override
//    public String updateStatus(long taskId, long employeeId, String status) {
//     Optional<EmployeeEntity> employeeEntityOpt=employeeRepository.findById(employeeId);
//       if (employeeEntityOpt.isPresent())
//       {
//           Optional<TaskEntity>taskEntityOpt=taskRepository.findById(taskId);
//           if(taskEntityOpt.isPresent()) {
//               taskEntityOpt.get().setStatus(status);
//               taskRepository.save(taskEntityOpt.get());
//               return "Task Status is updated + " + status;
//
//           }throw new TaskNotFound("There is no task with id "+taskId);
//
//       }throw new EmployeeNotFound("There is no Employee with id "+employeeId);
//    }

}
