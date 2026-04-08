package it.itconsulting.progettofinalebackendtommasogabriel.dto;

import java.time.LocalDate;

import it.itconsulting.progettofinalebackendtommasogabriel.model.Task;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskCreationDto {

    @NotBlank(message = "Title can not be blank")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Description can not be blank")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    private Boolean status;

    @NotNull(message = "Priority can not be null")
    private Task.Priority priority;
    
    @FutureOrPresent(message = "Task deadline cannot be in the past")
    private LocalDate deadlineTimestamp; // It can be null because is an optional field

    @NotNull(message = "UserId can not be null")
    private Long userId;

}