package com.ManagmentTask.Task.Respository;

import com.ManagmentTask.Task.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRespository extends JpaRepository<TaskEntity,Long> {
}
