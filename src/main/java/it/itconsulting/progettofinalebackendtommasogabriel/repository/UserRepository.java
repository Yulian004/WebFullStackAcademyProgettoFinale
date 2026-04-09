package it.itconsulting.progettofinalebackendtommasogabriel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.itconsulting.progettofinalebackendtommasogabriel.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
    public Optional<User> findByEmail(String email);
    
}
