package com.ManagmentTask.Task.DTOModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor

@Builder

public class TaskResponseModel {
    private long taskId;
    private String taskName;
    private String title;
    private String description;
    private String status;
    private LocalDateTime generatedTime;

    private Long taskAssignedToId;

    private Long taskAssignedById;

    public TaskResponseModel(long taskId,String taskName, String title, String description, String status, LocalDateTime generatedTime,Long taskAssignedById,Long taskAssignedToId ) {
        this.taskId=taskId;
        this.taskName=taskName;
        this.title=title;
        this.description=description;
        this.status=status;
        this.generatedTime=generatedTime;
        this.taskAssignedToId = taskAssignedToId;
        this.taskAssignedById = taskAssignedById;
    }
}
