package application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Livro {
    // Identificador único da tarefa
    @Id
    private Long id;
    
    private String titulo;

    private String generos;

    private String autores;
}