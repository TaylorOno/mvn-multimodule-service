package com.github.taylorono.task;

import com.github.taylorono.task.dtos.CreateTaskRequest;
import com.github.taylorono.task.dtos.TaskResponse;
import com.github.taylorono.task.exceptions.TaskNotFoundException;
import com.github.taylorono.task.repositories.entities.Task;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
@Validated
public class TaskController implements TaskService {

    private TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public TaskResponse addTask(@Valid @RequestBody CreateTaskRequest createTaskRequest) {
        TaskResponse task = createTaskResponse(taskService.addTask(new Task(createTaskRequest)));
        return task;
    }

    @GetMapping("/tasks")
    public List<TaskResponse> getTasks() {
        List<TaskResponse> taskList = taskService.getAllTasks().stream().map(this::createTaskResponse).collect(toList());
        return taskList;
    }

    @GetMapping("/tasks/{id}")
    public TaskResponse getTask(@PathVariable String id) throws TaskNotFoundException {
        TaskResponse task = taskService.getTask(id).map(this::createTaskResponse).orElseThrow(TaskNotFoundException::new);
        return task;
    }

    private TaskResponse createTaskResponse(Task task){
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTitle(task.getTitle());
        taskResponse.setDueDate(task.getDueDate());
        taskResponse.setPriority(task.getPriority());
        taskResponse.setDescription(task.getDescription());
        return taskResponse;
    }
}
