package com.ManagmentTask.Task.Respository;

import com.ManagmentTask.Task.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    Optional<EmployeeEntity> findByUserName(String UserName);
}
