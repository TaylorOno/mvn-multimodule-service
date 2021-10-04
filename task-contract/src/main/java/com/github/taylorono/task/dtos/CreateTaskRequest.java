package com.github.taylorono.task.dtos;

import lombok.*;;
import javax.validation.constraints.*;
import java.util.Date;

@NoArgsConstructor
@Data
public class CreateTaskRequest {

    @NotEmpty
    @Size(max = 10)
    private String title;

    @NotNull
    private Date dueDate;

    @Positive
    @Max(5)
    private int priority;

    private String description;

}
