package it.itconsulting.progettofinalebackendtommasogabriel.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.itconsulting.progettofinalebackendtommasogabriel.dto.TaskCreationDto;
import it.itconsulting.progettofinalebackendtommasogabriel.dto.TaskUpdateDto;
import it.itconsulting.progettofinalebackendtommasogabriel.exceptions.task.TaskNotFoundException;
import it.itconsulting.progettofinalebackendtommasogabriel.exceptions.task.TaskNotValidException;
import it.itconsulting.progettofinalebackendtommasogabriel.exceptions.user.UserNotFoundException;
import it.itconsulting.progettofinalebackendtommasogabriel.model.Task;
import it.itconsulting.progettofinalebackendtommasogabriel.model.User;
import it.itconsulting.progettofinalebackendtommasogabriel.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    UserService userService;

    public Task createTask(TaskCreationDto taskDto) throws TaskNotValidException, UserNotFoundException {
        if (taskDto == null) {
            throw new TaskNotValidException();
        }

        Optional<User> optionalUser = userService.getUserById(taskDto.getUserId());

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        User user = optionalUser.get();

        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus() != null ? taskDto.getStatus() : false);
        task.setPriority(taskDto.getPriority());
        task.setCreationTimestamp(LocalDate.now());
        task.setDeadlineTimestamp(taskDto.getDeadlineTimestamp());
        task.setUser(user);

        return taskRepository.save(task);
    }

    public Task updateTask(long id, TaskUpdateDto taskDto) throws TaskNotValidException, TaskNotFoundException {
        if (taskDto == null) {
            throw new TaskNotValidException();
        }

        Optional<Task> optionalTask = getTaskById(id);
        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException();
        }

        Task taskToUpdate = optionalTask.get();

        String newTitle = taskDto.getTitle();
        if (newTitle != null && !newTitle.isBlank()) {
            taskToUpdate.setTitle(newTitle);
        }

        String newDescription = taskDto.getDescription();
        if (newDescription != null && !newDescription.isBlank()) {
            taskToUpdate.setDescription(newDescription);
        }

        Boolean newStatus = taskDto.getStatus();
        if (newStatus != null) {
            taskToUpdate.setStatus(newStatus);
        }

        Task.Priority newPriority = taskDto.getPriority();
        if (newPriority != null) {
            taskToUpdate.setPriority(newPriority);
        }

        LocalDate deadlineTimestamp = taskDto.getDeadlineTimestamp();
        if (deadlineTimestamp != null) {
            taskToUpdate.setDeadlineTimestamp(deadlineTimestamp);
        }

        return taskRepository.save(taskToUpdate);
    }

    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    public Optional<Task> getTaskById(long id) {
        return taskRepository.findById(id);
    }

    public List<Task> getTasksByUserId(long userId) {
        return taskRepository.findByUserId(userId);
    }

}
