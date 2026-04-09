package it.itconsulting.progettofinalebackendtommasogabriel.exceptions;

import lombok.Data;

@Data
public class ResponseError {

    private String message;

    public ResponseError(String code, String message) {
        this.message = message;
    }

}
