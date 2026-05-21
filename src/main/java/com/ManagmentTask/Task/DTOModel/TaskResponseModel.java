package com.ManagmentTask.Task.DTOModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class TaskResponseModel {
    private Long taskId;
    private String taskName;
    private String title;
    private String description;
    private String status;
    private LocalDateTime generatedTime;

    private String taskAssignedToName;
    private String taskAssignedByName;


    private String taskAssignedToUserName;
    private String taskAssignedByUserName;

}
