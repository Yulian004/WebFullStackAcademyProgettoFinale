package it.itconsulting.progettofinalebackendtommasogabriel.exceptions.user;

public class UserNotFoundException extends UserException {
    
    public UserNotFoundException() {
        super("Utente non valido");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
