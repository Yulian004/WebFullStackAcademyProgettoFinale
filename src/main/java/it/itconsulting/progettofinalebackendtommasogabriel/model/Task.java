package it.itconsulting.progettofinalebackendtommasogabriel.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "tasks")
@Data
@NoArgsConstructor
@ToString(exclude = "user")
@JsonIgnoreProperties("user")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Boolean status;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Column(name = "creation_timestamp", nullable = false)
    private LocalDateTime creationTimestamp;
    @Column(name = "deadline_timestamp")
    private LocalDate deadlineTimestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty("userId")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    public enum Priority {
        LOW,
        MEDIUM,
        HIGH;
    }

}
