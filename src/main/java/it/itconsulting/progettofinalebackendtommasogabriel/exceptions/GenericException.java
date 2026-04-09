package it.itconsulting.progettofinalebackendtommasogabriel.exceptions;

public class GenericException extends Exception {

    public GenericException() {
        super("Errore inaspettato");
    }

    public GenericException(String message) {
        super(message);
    }
    
}
