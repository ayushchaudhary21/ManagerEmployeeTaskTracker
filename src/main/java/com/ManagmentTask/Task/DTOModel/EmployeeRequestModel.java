package com.ManagmentTask.Task.DTOModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestModel {
    private long employeeId;
    private String employeeName;
    private String userName;
    private String password;
    private String role;


}
