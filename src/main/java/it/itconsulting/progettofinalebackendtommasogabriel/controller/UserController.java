package it.itconsulting.progettofinalebackendtommasogabriel.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.itconsulting.progettofinalebackendtommasogabriel.dto.UserSignInDto;
import it.itconsulting.progettofinalebackendtommasogabriel.exceptions.user.UserNotValidException;
import it.itconsulting.progettofinalebackendtommasogabriel.dto.UserDto;
import it.itconsulting.progettofinalebackendtommasogabriel.model.User;
import it.itconsulting.progettofinalebackendtommasogabriel.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> signUpUser(@RequestBody @Validated UserDto userDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String stringifiedErrors = bindingResult.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining("\n"));

            return new ResponseEntity<>(stringifiedErrors, HttpStatus.BAD_REQUEST);
        }

        try {
            if (userService.checkUserExistence(userDto.getEmail())) {
                return new ResponseEntity<>("Un account con questa email è già presente", HttpStatus.BAD_REQUEST);
            }
            User user = userService.createUser(userDto);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (UserNotValidException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Errore inaspettato", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody @Validated UserSignInDto userSignInDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String stringifiedErrors = bindingResult.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining("\n"));

            return new ResponseEntity<>(stringifiedErrors, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<User> optionalUser = userService.getUserByEmail(userSignInDto.getEmail());

            if (optionalUser.isEmpty()) {
                return new ResponseEntity<>("Utente non trovato", HttpStatus.BAD_REQUEST);
            }

            User user = optionalUser.get();
            if (!user.getPassword().equals(userSignInDto.getPassword())) {
                return new ResponseEntity<>("Password sbagliata", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Errore inaspettato", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
