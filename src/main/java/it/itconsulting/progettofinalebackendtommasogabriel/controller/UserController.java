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
import it.itconsulting.progettofinalebackendtommasogabriel.dto.UserSignUpDto;
import it.itconsulting.progettofinalebackendtommasogabriel.model.User;
import it.itconsulting.progettofinalebackendtommasogabriel.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> signUpUser(@RequestBody @Validated UserSignUpDto userSignUpDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String stringifiedErrors = bindingResult.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining("\n"));

            Error error = new Error(stringifiedErrors);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<User> checkUserExistence = userService.getUserByEmail(userSignUpDto.getEmail());
            if(checkUserExistence.isPresent()) {
                Error error = new Error("An account with this email address already exists");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            User user = userService.createUser(userSignUpDto);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            Error error = new Error(e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Error error = new Error("An error occurred");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody @Validated UserSignInDto userSignInDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String stringifiedErrors = bindingResult.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.joining("\n"));

            Error error = new Error(stringifiedErrors);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<User> optionalUser = userService.getUserByEmail(userSignInDto.getEmail());

            if (optionalUser.isEmpty()) {
                Error error = new Error("User with this email not found");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            User user = optionalUser.get();
            if (!user.getPassword().equals(userSignInDto.getPassword())) {
                Error error = new Error("Wrong password");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            Error error = new Error(e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Error error = new Error("An error occurred");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
