package application.model;

import application.record.LivroDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Livro {
    // Identificador único da tarefa
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String generos;
    private String autores;

    public Livro(LivroDTO record) {
        this.id = record.id();
        this.titulo = record.titulo();
        this.generos = record.generos();
        this.autores = record.autores();
    }
}