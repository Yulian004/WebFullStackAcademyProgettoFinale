package it.itconsulting.progettofinalebackendtommasogabriel.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.itconsulting.progettofinalebackendtommasogabriel.dto.TaskCreationDto;
import it.itconsulting.progettofinalebackendtommasogabriel.dto.TaskUpdateDto;
import it.itconsulting.progettofinalebackendtommasogabriel.model.Task;
import it.itconsulting.progettofinalebackendtommasogabriel.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{userId}")
    public List<Task> getAllUserTasks(@PathVariable long userId) {
        return taskService.getTasksByUserId(userId);
    }

    @PostMapping("")
    public ResponseEntity<Object> createTask(@RequestBody @Validated TaskCreationDto taskCreationDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String stringifiedErrors = bindingResult.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining("\n"));

            Error error = new Error(stringifiedErrors);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        try {
            Task task = taskService.createTask(taskCreationDto);
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            Error error = new Error(e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Error error = new Error("An error occurred");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable long id, @RequestBody @Validated TaskUpdateDto taskUpdateDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String stringifiedErrors = bindingResult.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining("\n"));

            Error error = new Error(stringifiedErrors);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        try {
            Task updatedTask = taskService.updateTask(id, taskUpdateDto);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            Error error = new Error(e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Error error = new Error("An error occurred");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable long id) {
        try {
            taskService.deleteTask(id);
            return new ResponseEntity<>((Object) null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            Error error = new Error("An error occurred");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
