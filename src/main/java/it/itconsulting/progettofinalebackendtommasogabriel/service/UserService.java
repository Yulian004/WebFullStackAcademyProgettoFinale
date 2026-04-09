package it.itconsulting.progettofinalebackendtommasogabriel.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.itconsulting.progettofinalebackendtommasogabriel.dto.UserDto;
import it.itconsulting.progettofinalebackendtommasogabriel.exceptions.user.UserNotFoundException;
import it.itconsulting.progettofinalebackendtommasogabriel.exceptions.user.UserNotValidException;
import it.itconsulting.progettofinalebackendtommasogabriel.model.User;
import it.itconsulting.progettofinalebackendtommasogabriel.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDto userDto) throws UserNotValidException {
        if (userDto == null) {
            throw new UserNotValidException();
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCreationTimestamp(LocalDate.now());

        return userRepository.save(user);
    }

    public User updateUser(UserDto userDto, long id) throws UserNotValidException, UserNotFoundException {
        if (userDto == null) {
            throw new UserNotValidException();
        }

        Optional<User> optionalUser = getUserById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        User userToUpdate = optionalUser.get();
        String newEmail = userDto.getEmail();
        if (newEmail != null && !newEmail.isBlank()) {
            userToUpdate.setEmail(newEmail);
        }
        String newPassword = userDto.getPassword();
        if (newPassword != null && !newPassword.isBlank()) {
            userToUpdate.setPassword(newPassword);
        }
        String newUsername = userDto.getUsername();
        if (newUsername != null && !newUsername.isBlank()) {
            userToUpdate.setUsername(newUsername);
        }

        return userRepository.save(userToUpdate);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Boolean checkUserExistence(String email) {
        Optional<User> checkUserExistence = getUserByEmail(email);
        if (checkUserExistence.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

}
