package it.itconsulting.progettofinalebackendtommasogabriel.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.itconsulting.progettofinalebackendtommasogabriel.dto.UserSignUpDto;
import it.itconsulting.progettofinalebackendtommasogabriel.model.User;
import it.itconsulting.progettofinalebackendtommasogabriel.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserSignUpDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("User not valid");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCreationTimestamp(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User updateUser(UserSignUpDto userDto, long id) {
        if (userDto == null) {
            throw new IllegalArgumentException("User not valid");
        }

        Optional<User> optionalUser = getUserById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User with such id not found");
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

}
