package com.ManagmentTask.Task.DTOModel;

import com.ManagmentTask.Task.Entity.TaskEntity;
import com.ManagmentTask.Task.Enum.EmployeeRole;
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
    private Long employeeId;
    private String employeeName;
    private String userName;
    private EmployeeRole role;


}
