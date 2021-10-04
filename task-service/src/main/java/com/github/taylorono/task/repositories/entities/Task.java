package com.github.taylorono.task.repositories.entities;

import com.github.taylorono.task.dtos.CreateTaskRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Task {

    @Id
    private String id;
    private String title;
    private Date dueDate;
    private int priority;
    private String description;

    public Task(CreateTaskRequest createTaskRequest) {
        id = UUID.randomUUID().toString();
        title = createTaskRequest.getTitle();
        dueDate = createTaskRequest.getDueDate();
        priority = createTaskRequest.getPriority();
        description = createTaskRequest.getDescription();
    }
}
