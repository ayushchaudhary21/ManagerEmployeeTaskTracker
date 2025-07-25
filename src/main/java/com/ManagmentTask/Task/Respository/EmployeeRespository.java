package com.ManagmentTask.Task.Respository;

import com.ManagmentTask.Task.Controller.EmployeeController;
import com.ManagmentTask.Task.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRespository extends JpaRepository<EmployeeEntity,Long> {
}
