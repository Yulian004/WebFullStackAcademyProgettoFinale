package it.itconsulting.progettofinalebackendtommasogabriel.exceptions.task;

public class TaskNotValidException extends TaskException {
    
    public TaskNotValidException() {
        super("Task non valida");
    }

    public TaskNotValidException(String message) {
        super(message);
    }

}
