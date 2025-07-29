package com.ManagmentTask.Task.Respository;

import com.ManagmentTask.Task.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long> {
     List<TaskEntity> findByAssignedBy_EmployeeId(long id);  //taskEntity.assignedBy.employeeId == id
     //  findBy[ObjectField]_[FieldInsideObject]

      List<TaskEntity>findByAssignedTo_EmployeeId(long id);
}

