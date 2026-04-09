package it.itconsulting.progettofinalebackendtommasogabriel.exceptions.task;

public abstract class TaskException extends Exception {

    public TaskException() {
    }

    public TaskException(String message) {
        super(message);
    }

}
