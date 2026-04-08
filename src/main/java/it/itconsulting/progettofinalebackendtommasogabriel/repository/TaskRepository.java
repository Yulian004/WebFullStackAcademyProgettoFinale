package it.itconsulting.progettofinalebackendtommasogabriel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.itconsulting.progettofinalebackendtommasogabriel.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> findByUserId(long userId);
}
