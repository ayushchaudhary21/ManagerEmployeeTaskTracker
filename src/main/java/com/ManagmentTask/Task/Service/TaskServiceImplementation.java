package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.Entity.TaskEntity;
import com.ManagmentTask.Task.Respository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImplementation implements TaskServiceInterface {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public String generateTask(long id, TaskEntity taskEntity) {
        return "";
    }

    @Override
    public List<TaskEntity> returnAllTask(long managerId) {
        return List.of();
    }

    @Override
    public List<TaskEntity> returnTask(long employeeTask) {
        return List.of();
    }

    @Override
    public String assignedTask(long employeeId, long taskId) {
        return "";
    }

    @Override
    public String updateStatus(long taskId, long employeeId, String status) {
        return "";
    }
}
