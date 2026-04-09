package it.itconsulting.progettofinalebackendtommasogabriel.exceptions.user;

public class UserWrongPasswordException extends UserException {
    
    public UserWrongPasswordException() {
        super("Password errata");
    }

    public UserWrongPasswordException(String message) {
        super(message);
    }

}
