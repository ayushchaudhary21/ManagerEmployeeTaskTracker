package com.ManagmentTask.Task.Entity;

import com.ManagmentTask.Task.Enum.EmployeeRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String employeeName;

    private String userName;
    private String password;

    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    @OneToMany(mappedBy = "taskAssignedBy",cascade = CascadeType.ALL)
    private List<TaskEntity>createdTask;


    @OneToMany(mappedBy = "taskAssignedTo",cascade = CascadeType.ALL)

    private List<TaskEntity>assinedTask;


}
