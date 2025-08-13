package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.Entity.EmployeeEntity;
import com.ManagmentTask.Task.Entity.TaskEntity;

import java.util.EnumMap;
import java.util.List;

public interface TaskServiceInterface {
    String generateTask(String  userName, TaskEntity taskEntity);
    // Manager to see all the task assigned.
    List<TaskEntity> returnAllTask(String userName);
    List<TaskEntity>returnTask(String userName);
//    String assignedTask(String userName,long taskId);
//    String updateStatus(long taskId,String userName,String status);

}
