package com.ManagmentTask.Task.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long taskId;
    private String taskName;
    private String title;
    private String description;
    private String status;
    private LocalDateTime generatedTime;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private EmployeeEntity assignedTo;

    @ManyToOne
    @JoinColumn(name="manager_id")
    private EmployeeEntity assignedBy;

    @PrePersist
    public void onCreate() {
        this.generatedTime = LocalDateTime.now();
    }
}
