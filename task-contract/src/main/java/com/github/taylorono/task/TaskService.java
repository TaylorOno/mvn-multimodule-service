package com.github.taylorono.task;

import com.github.taylorono.task.dtos.CreateTaskRequest;
import com.github.taylorono.task.dtos.TaskResponse;
import com.github.taylorono.task.exceptions.TaskNotFoundException;

import java.util.List;

public interface TaskService {
    TaskResponse addTask(CreateTaskRequest createTaskRequest);
    List<TaskResponse> getTasks();
    TaskResponse getTask(String id) throws TaskNotFoundException;
}
