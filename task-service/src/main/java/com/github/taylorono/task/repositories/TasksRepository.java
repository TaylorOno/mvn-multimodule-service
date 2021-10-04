package com.github.taylorono.task.repositories;

import com.github.taylorono.task.repositories.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends CrudRepository<Task, String> {
    List<Task> findAll();
}
