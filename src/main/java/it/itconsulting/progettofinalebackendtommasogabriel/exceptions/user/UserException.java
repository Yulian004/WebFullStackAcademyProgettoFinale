package it.itconsulting.progettofinalebackendtommasogabriel.exceptions.user;

public abstract class UserException extends Exception {

    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

}
