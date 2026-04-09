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
public class TaskUpdateDto {
    
    @NotBlank(message = "Il titolo non può essere vuoto")
    @Size(min = 3, max = 100, message = "Il titolo deve avere una lunghezza compresa tra 3 e 100 caratteri")
    private String title;

    @NotBlank(message = "La descrizione non può essere vuota")
    @Size(max = 1000, message = "La descrizione non può superare i 1000 caratteri")
    private String description;

    private Boolean status;

    @NotNull(message = "La priorità non può essere nulla")
    private Task.Priority priority;
    
    @FutureOrPresent(message = "La scadenza del task non può essere nel passato")
    private LocalDate deadlineTimestamp; // It is optional, so it can be null

}