package com.github.taylorono.task;

import com.github.taylorono.task.dtos.CreateTaskRequest;
import com.github.taylorono.task.dtos.TaskResponse;
import com.github.taylorono.task.exceptions.TaskNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class TaskClient implements TaskService {
    private final Logger logger = LoggerFactory.getLogger(TaskClient.class);

    private final String serviceUrl;
    private final RestTemplate restTemplate;

    public TaskClient(RestTemplate restTemplate, String serviceUrl) {
        this.restTemplate = restTemplate;
        this.serviceUrl = serviceUrl;
    }

    @Override
    public TaskResponse addTask(CreateTaskRequest createTaskRequest) {
        return restTemplate.postForObject(serviceUrl +"/tasks", createTaskRequest, TaskResponse.class);
    }

    @Override
    public List<TaskResponse> getTasks() {
        return restTemplate.getForObject(serviceUrl+"/tasks", List.class);
    }

    @Override
    public TaskResponse getTask(String id) throws TaskNotFoundException {
        return restTemplate.getForObject(serviceUrl+"/tasks/{id}", TaskResponse.class, id);
    }
}
