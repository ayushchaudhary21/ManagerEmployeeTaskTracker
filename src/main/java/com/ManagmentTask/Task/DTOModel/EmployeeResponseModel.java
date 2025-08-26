package com.ManagmentTask.Task.DTOModel;

import com.ManagmentTask.Task.Entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseModel {
    private long employeeId;
    private String Employeename;
    private String userName;
    private String role;
   private List<TaskEntity> createdBy;
   private List<TaskEntity>assignedTo;

}
