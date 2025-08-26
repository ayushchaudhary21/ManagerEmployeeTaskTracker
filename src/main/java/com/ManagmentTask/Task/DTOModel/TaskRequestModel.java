package com.ManagmentTask.Task.DTOModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequestModel {
    private String taskName;
    private String title;
    private String description;
    private String status;

    private Long assignedToId;
    private Long assignedById;
}
