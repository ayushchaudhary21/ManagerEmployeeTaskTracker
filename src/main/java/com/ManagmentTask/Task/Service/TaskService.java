package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.DTOModel.TaskRequestModel;
import com.ManagmentTask.Task.DTOModel.TaskResponseModel;
import com.ManagmentTask.Task.Entity.EmployeeEntity;
import com.ManagmentTask.Task.Entity.TaskEntity;
import com.ManagmentTask.Task.Enum.EmployeeRole;
import com.ManagmentTask.Task.Exception.EmployeeNotFound;
import com.ManagmentTask.Task.Exception.NotDetermineRole;
import com.ManagmentTask.Task.Mapper.EmployeeMapper;
import com.ManagmentTask.Task.Mapper.TaskMapper;
import com.ManagmentTask.Task.Respository.EmployeeRepository;
import com.ManagmentTask.Task.Respository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements TaskServiceInterface{
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskMapper mapper;

    public TaskService(TaskRepository taskRepository, EmployeeRepository employeeRepository, TaskMapper mapper) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public String generateTask(String userName, TaskRequestModel taskRequestModel,Long id) {
      EmployeeEntity  manger=employeeRepository.findByUserName(userName)
                                                              .orElseThrow(()->new RuntimeException("Manager is not found with "+userName));
        EmployeeEntity employee=employeeRepository.findById(id).orElseThrow(()->new RuntimeException("user is not found with id: "+id));

        if(!manger.getRole().equals(EmployeeRole.Manager))
        {
              throw new NotDetermineRole(userName+" is not manager ");
        }
        TaskEntity taskEntity=mapper.DtoToTask(taskRequestModel);
        taskEntity.setTaskAssignedBy(manger);
        taskEntity.setTaskAssignedTo(employee);
        taskEntity.setGeneratedTime(LocalDateTime.now());
        taskRepository.save(taskEntity);
        return "Task is create with task id "+taskEntity.getTaskId() +" Assigned to "+employee.getEmployeeName();
    }

    @Override
    public List<TaskResponseModel> returnManagerCreatedTask(String userName) {
        EmployeeEntity employeeEntity=employeeRepository.findByUserName(userName)
                .orElseThrow(()->new EmployeeNotFound("No Manager is found with the username "+userName));

        if(!employeeEntity.getRole().equals(EmployeeRole.Manager))
        {
           throw new NotDetermineRole("UserName does belong to the manager");
        }
        return taskRepository.findByTaskAssignedBy_EmployeeId(employeeEntity.getEmployeeId())
                .stream().map(mapper::entityToModel)
                .toList();
    }

    @Override
    public List<TaskResponseModel> employeereturnTask(String userName) {
        EmployeeEntity employeeEntity=employeeRepository.findByUserName(userName)
                .orElseThrow(()->new EmployeeNotFound("No Employee is found with the username "+userName));

        if(!employeeEntity.getRole().equals(EmployeeRole.Developer))
        {
            throw new NotDetermineRole("UserName does belong to the Developer");
        }
        return taskRepository.findByTaskAssignedTo_EmployeeId(employeeEntity.getEmployeeId())
                .stream().map(mapper::entityToModel)
                .toList();
    }


    @Override
    public String updateStatus(String title, String userName, String status) {
        return "";
    }
}
