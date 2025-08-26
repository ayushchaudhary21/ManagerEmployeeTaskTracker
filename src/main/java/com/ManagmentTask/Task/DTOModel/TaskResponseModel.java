package com.ManagmentTask.Task.DTOModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TaskResponseModel {
    private long taskId;
    private String taskName;
    private String title;
    private String description;
    private String status;
    private LocalDateTime generatedTime;

    private Long assignedToId;
    private String assignedToName;

    private Long assignedById;
    private String assignedByName;
}
