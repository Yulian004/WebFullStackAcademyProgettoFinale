package it.itconsulting.progettofinalebackendtommasogabriel.exceptions.task;

public class TaskNotFoundException extends TaskException {

    public TaskNotFoundException() {
        super("Task non trovata");
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
    
}
