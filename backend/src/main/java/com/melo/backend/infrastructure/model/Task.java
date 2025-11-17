package com.melo.backend.infrastructure.model;

import com.melo.backend.infrastructure.enums.TaskPriority;
import com.melo.backend.infrastructure.enums.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
//import lombok.NoArgsConstructor; -- consertar
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Builder
@AllArgsConstructor
@Getter
@Setter
public class Task {

    public Task() {
        //TODO Auto-generated constructor stub
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 255)
    private String title;

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 1024)
    private String description;

    // alterar para formato real de data e hora
    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 255)
    private String dateAndHour;

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 255)
    private TaskStatus status;

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 255)
    private TaskPriority priority;

    /**
     * Foreign key to user
     */
    @NotBlank
    @NotNull
    @NotEmpty
    private Long fk_user_id;


}
