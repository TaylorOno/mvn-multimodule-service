package com.github.taylorono.task.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TaskResponse {
    private String id;
    private String title;
    private Date dueDate;
    private int priority;
    private String description;
}
