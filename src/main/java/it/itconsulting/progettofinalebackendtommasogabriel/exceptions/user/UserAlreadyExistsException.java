package it.itconsulting.progettofinalebackendtommasogabriel.exceptions.user;

public class UserAlreadyExistsException extends UserException {

    public UserAlreadyExistsException() {
        super("Un account con questa email è già presente");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
    
}
