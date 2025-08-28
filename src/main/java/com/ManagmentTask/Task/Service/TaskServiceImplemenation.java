package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.DTOModel.TaskRequestModel;
import com.ManagmentTask.Task.DTOModel.TaskResponseModel;
import com.ManagmentTask.Task.Entity.EmployeeEntity;
import com.ManagmentTask.Task.Entity.TaskEntity;
import com.ManagmentTask.Task.Exception.*;
import com.ManagmentTask.Task.Respository.EmployeeRepository;
import com.ManagmentTask.Task.Respository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TaskServiceImplemenation implements TaskServiceInterface{
    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImplemenation(EmployeeRepository employeeRepository, TaskRepository taskRepository) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
    }
    private final String manager="manager";
    @Override
    public String generateTask(String userName, TaskRequestModel taskRequestModel) {
        Optional<EmployeeEntity> employeeEntityOptional =employeeRepository.findByUserName(userName);
        Optional<TaskEntity> taskEntityOptional=taskRepository.findByTitle(taskRequestModel.getTitle());
        if(taskEntityOptional.isPresent()) throw new DublicateTask("Task Already Generated "+taskRequestModel.getTaskName());
        else if(employeeEntityOptional.isPresent() && employeeEntityOptional.get().getRoles().equalsIgnoreCase(manager) )
        {
            TaskEntity taskEntity=new TaskEntity();
             taskEntity.setTaskName(taskRequestModel.getTaskName());
             taskEntity.setTitle(taskRequestModel.getTitle());
             taskEntity.setDescription(taskRequestModel.getDescription());
             taskEntity.setTaskAssignedBy(employeeEntityOptional.get());
             taskEntity.setStatus(taskRequestModel.getStatus());
              taskRepository.save(taskEntity);
            return " Task created successfully by Manager's Name: " + employeeEntityOptional.get().getEmployeeName();
        }
        throw  new EmployeeNotFound(" Task creation failed: Employee with UserName" + userName + " is not a manager or does not exist.");
    }

    @Override
    public List<TaskResponseModel> returnAllTask(String userName) {
        // provide all the tasks that are created by the specific Manager.
        Optional<EmployeeEntity>employeeEntityDB=employeeRepository.findByUserName(userName);
        if(employeeEntityDB.isPresent() && employeeEntityDB.get().getRoles().equalsIgnoreCase(manager))
        {
            List<TaskEntity>taskEntityList=taskRepository.findByTaskAssignedBy_EmployeeId(employeeEntityDB.get().getEmployeeId());
            return taskEntityList.stream().map(task -> new TaskResponseModel(
                     task.getTaskId(),
                    task.getTaskName(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getStatus(),
                    task.getGeneratedTime(),
                    task.getTaskAssignedTo() != null ? task.getTaskAssignedTo().getEmployeeId() : null,
                    task.getTaskAssignedBy() != null ? task.getTaskAssignedBy().getEmployeeId() : null
            )).toList();
        }
        throw new EmployeeNotFound("No task are assigned by the manager userName : "+userName );
    }

    @Override
    public List<TaskResponseModel> returnTask(String userName) {
        // return all the tasks that are assigned to the particular Employee.
        Optional<EmployeeEntity> employeeEntityOptional =employeeRepository.findByUserName(userName);
        if(employeeEntityOptional.isPresent())
        {
           List<TaskEntity> taskEntityList=  taskRepository.findByTaskAssignedTo_EmployeeId(employeeEntityOptional.get().getEmployeeId());
             return  taskEntityList.stream().map( taskEntity -> new TaskResponseModel(
                     taskEntity.getTaskId(),
                     taskEntity.getTaskName(),
                     taskEntity.getTitle(),
                     taskEntity.getDescription(),
                     taskEntity.getStatus(),
                     taskEntity.getGeneratedTime(),
                     taskEntity.getTaskAssignedTo() != null ? taskEntity.getTaskAssignedTo().getEmployeeId() : null,
                     taskEntity.getTaskAssignedBy() != null ? taskEntity.getTaskAssignedBy().getEmployeeId() : null
             )).toList();
        }throw new EmployeeNotFound("There is no employee with userName :"+userName);
    }

    @Override
    public String assignedTask(String managerUserName, String title,String employeeUserName) {
        // Manager(UserName) -> assigned the Task Particular Employee -> with particular Title
       Optional<TaskEntity> taskEntityOptional =taskRepository.findByTitle(title);
       Optional<EmployeeEntity> mangerEntityOptional =employeeRepository.findByUserName(managerUserName);
       Optional<EmployeeEntity>employeeEntityOptional=employeeRepository.findByUserName(employeeUserName);
       if(mangerEntityOptional.isPresent() && !mangerEntityOptional.get().getRoles().equalsIgnoreCase(manager))
       {
           throw new NotDetermineRole("UserName is assigned to Manger");
       }
        if(taskEntityOptional.isPresent())
        {
            if(mangerEntityOptional.isPresent()) {
                if (taskEntityOptional.get().getTaskAssignedTo() == null) {
                     if(employeeEntityOptional.isPresent()) {
                         taskEntityOptional.get().setTaskAssignedTo(employeeEntityOptional.get());
                          taskRepository.save(taskEntityOptional.get());
                         return "Task " + title + "is assigned to " + employeeUserName;
                     }else throw new EmployeeNotFound("No employee found with the username "+employeeUserName);
                }throw new MultiTaskAssigned("Task is Already assigned");

            } throw new EmployeeNotFound("There is no employee with userName: "+ managerUserName);

        }throw new TaskNotFound("there is no task with id :" +title);

    }

    @Override
    public String updateStatus(String title, String  employeeUserName, String status) {
     Optional<EmployeeEntity> employeeEntityOpt=employeeRepository.findByUserName(employeeUserName);
       if (employeeEntityOpt.isPresent())
       {
           Optional<TaskEntity>taskEntityOpt=taskRepository.findByTitle(title);
           if(taskEntityOpt.isPresent()) {
               if(taskEntityOpt.get().getStatus().equalsIgnoreCase("pending") ) {
                   taskEntityOpt.get().setStatus(status);
                   taskRepository.save(taskEntityOpt.get());
                   return "Task Status is updated + " + status;
               }
           }throw new TaskNotFound("There is no task with id "+title);

       }throw new EmployeeNotFound("There is no Employee with id "+employeeUserName);
    }

}

