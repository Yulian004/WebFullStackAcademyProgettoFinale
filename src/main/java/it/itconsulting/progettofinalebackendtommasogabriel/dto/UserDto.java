package it.itconsulting.progettofinalebackendtommasogabriel.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "Lo username è obbligatorio")
    @Size(min = 4, max = 20, message = "Lo username deve avere una lunghezza compresa tra 4 e 20 caratteri")
    private String username;

    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "Inserisci un indirizzo email valido")
    @Size(max = 50, message = "L'email non può superare i 50 caratteri")
    private String email;

    @NotBlank(message = "La password è obbligatoria")
    @Size(min = 8, max = 64, message = "La password deve avere una lunghezza compresa tra 8 e 64 caratteri")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z]).{8,}$", message = "La password è troppo debole (deve contenere almeno un numero e una lettera)")
    private String password;

}
