package com.github.taylorono.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.taylorono.task.repositories.entities.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@SpringBootTest
@AutoConfigureWebTestClient
public class TaskControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void tasks_returns200_andTaskResponse() throws Exception {
        webTestClient.post()
                .uri("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"title\": \"New Task\", \"dueDate\": \"2021-01-01\", \"priority\": 1 }")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("title").isEqualTo("New Task")
                .jsonPath("dueDate").isEqualTo("2021-01-01T00:00:00.000+00:00")
                .jsonPath("priority").isEqualTo(1);
    }

    @Test
    public void task_returns200_andTaskResponse() throws Exception {
        webTestClient.post()
                .uri("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"title\": \"New Task\", \"dueDate\": \"2021-01-01\", \"priority\": 1 }")
                .exchange()
                .expectStatus().isOk();

        webTestClient.get()
                .uri("/tasks")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").value(greaterThanOrEqualTo(1));
    }

    @Test
    public void task_withId_returns200_andTaskResponse() throws Exception {
        byte[] result = webTestClient.post()
                .uri("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"title\": \"New Task\", \"dueDate\": \"2021-01-01\", \"priority\": 1 }")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .returnResult().getResponseBody();

        Task task = objectMapper.readValue(result, Task.class);

        webTestClient.get()
                .uri("/tasks/{id}", task.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("id").isEqualTo(task.getId());
    }

    @Test
    public void task_withBadId_returns404() throws Exception {
        webTestClient.get()
                .uri("/tasks/bad-id")
                .exchange()
                .expectStatus().isNotFound();
    }
}