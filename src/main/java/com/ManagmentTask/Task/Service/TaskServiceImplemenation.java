package com.ManagmentTask.Task.Service;

import com.ManagmentTask.Task.DTOModel.TaskRequestModel;
import com.ManagmentTask.Task.DTOModel.TaskResponseModel;
import com.ManagmentTask.Task.Respository.EmployeeRepository;
import com.ManagmentTask.Task.Respository.TaskRepository;

import java.util.List;

public class TaskServiceImplemenation implements TaskServiceInterface{
    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImplemenation(EmployeeRepository employeeRepository, TaskRepository taskRepository) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public String generateTask(String userName, TaskRequestModel taskRequestModel) {
        return "";
    }

    @Override
    public List<TaskResponseModel> returnAllTask(String userName) {
        return List.of();
    }

    @Override
    public List<TaskResponseModel> returnTask(String userName) {
        return List.of();
    }
}
