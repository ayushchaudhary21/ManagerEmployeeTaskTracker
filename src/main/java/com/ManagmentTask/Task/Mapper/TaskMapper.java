package com.ManagmentTask.Task.Mapper;

import com.ManagmentTask.Task.DTOModel.TaskRequestModel;
import com.ManagmentTask.Task.DTOModel.TaskResponseModel;
import com.ManagmentTask.Task.Entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "taskId", ignore = true)
    @Mapping(target = "generatedTime", ignore = true)
    @Mapping(target = "taskAssignedTo", ignore = true)
    @Mapping(target = "taskAssignedBy", ignore = true)
    TaskEntity DtoToTask(TaskRequestModel taskRequestModel);
    @Mapping(target = "taskAssignedToName",     source = "taskAssignedTo.employeeName")
    @Mapping(target = "taskAssignedToUserName", source = "taskAssignedTo.userName")
    @Mapping(target = "taskAssignedByName",     source = "taskAssignedBy.employeeName")
    @Mapping(target = "taskAssignedByUserName", source = "taskAssignedBy.userName")
    TaskResponseModel entityToModel(TaskEntity entity);
}
