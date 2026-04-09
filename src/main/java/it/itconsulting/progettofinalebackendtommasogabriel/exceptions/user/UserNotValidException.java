package it.itconsulting.progettofinalebackendtommasogabriel.exceptions.user;

public class UserNotValidException extends UserException {
    
    public UserNotValidException() {
        super("Utente non valido");
    }

    public UserNotValidException(String message) {
        super(message);
    }

}
