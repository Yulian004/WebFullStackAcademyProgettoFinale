package it.itconsulting.progettofinalebackendtommasogabriel.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSignInDto {
    @NotBlank(message = "Email richiesta")
    @Email(message = "Inserisci una email valida")
    private String email;

    @NotBlank(message = "Password richiesta")
    private String password;
}
