package com.github.taylorono.task;

import com.github.taylorono.task.repositories.TasksRepository;
import com.github.taylorono.task.repositories.entities.Task;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl {
    private TasksRepository tasksRepository;

    public TaskServiceImpl(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Task addTask(Task t) {
        return tasksRepository.save(t);
    }

    public List<Task> getAllTasks() {
        return tasksRepository.findAll();
    }

    @Cacheable("getTask")
    public Optional<Task> getTask(String id) {
        return tasksRepository.findById(id);
    }
}
