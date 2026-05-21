package com.ManagmentTask.Task.DTOModel;

import com.ManagmentTask.Task.Enum.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestModel {

    private String employeeName;
    private String userName;
    private String password;
    private EmployeeRole role;


}
