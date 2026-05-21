package com.ManagmentTask.Task.Mapper;

import com.ManagmentTask.Task.DTOModel.EmployeeRequestModel;
import com.ManagmentTask.Task.DTOModel.EmployeeResponseModel;
import com.ManagmentTask.Task.Entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target="password",ignore=true)
    @Mapping(target="employeeId",ignore = true)

    EmployeeEntity dtoToEntity(EmployeeRequestModel employeeRequest);

    EmployeeResponseModel entityToDTO(EmployeeEntity employee);
}
