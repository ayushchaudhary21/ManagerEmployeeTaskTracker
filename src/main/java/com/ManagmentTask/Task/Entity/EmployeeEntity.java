package com.ManagmentTask.Task.Entity;

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
    private long employeeId;
    private String name;

    private String userName;
    private String password;

    private String roles;

    @OneToMany(mappedBy = "assignedBy",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TaskEntity>CreatedTask;


    @OneToMany(mappedBy = "assignedTo",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TaskEntity>assinedTask;


}
