package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.DTOModel.TaskRequestModel;
import com.ManagmentTask.Task.DTOModel.TaskResponseModel;
import com.ManagmentTask.Task.Entity.EmployeeEntity;
import com.ManagmentTask.Task.Entity.TaskEntity;
import com.ManagmentTask.Task.Exception.EmployeeNotFound;

import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

public interface TaskServiceInterface {
    String generateTask(String  userName, TaskRequestModel taskRequestModel);
    // Manager to see all the task assigned.
    List<TaskResponseModel> returnAllTask(String userName);
    List<TaskResponseModel>returnTask(String userName);
//    String assignedTask(String userName,long taskId);
//    String updateStatus(long taskId,String userName,String status);

}
