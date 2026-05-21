package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.DTOModel.TaskRequestModel;
import com.ManagmentTask.Task.DTOModel.TaskResponseModel;
import com.ManagmentTask.Task.Entity.EmployeeEntity;
import com.ManagmentTask.Task.Entity.TaskEntity;
import com.ManagmentTask.Task.Exception.EmployeeNotFound;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
@Service
public interface TaskServiceInterface {
    String generateTask(String  userName, TaskRequestModel taskRequestModel,Long employeeId);
    // Manager to see all the task assigned.
    List<TaskResponseModel> returnManagerCreatedTask(String userName);
    List<TaskResponseModel>employeereturnTask(String userName);
   // String assignedTask(String managerUserName,String title,String employeeUserName );
    String updateStatus(String title,String userName,String status);

}
