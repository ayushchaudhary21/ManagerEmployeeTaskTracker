package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.Entity.EmployeeEntity;
import com.ManagmentTask.Task.Entity.TaskEntity;

import java.util.EnumMap;
import java.util.List;

public interface TaskServiceInterface {
    String generateTask(long id, TaskEntity taskEntity);
    // Manager to see all the task assigned.
    List<TaskEntity> returnAllTask(long managerId);
    List<TaskEntity>returnTask(long employeeTask);
    String assignedTask(long employeeId,long taskId);
    String updateStatus(long taskId,long employeeId,String status);

}
