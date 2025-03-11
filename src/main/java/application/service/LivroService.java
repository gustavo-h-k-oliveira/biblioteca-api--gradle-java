package application.service;

import application.model.Livro;
import application.record.LivroDTO;
import application.repository.LivroRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepo;

    public Iterable<LivroDTO> getAll() {
        return livroRepo.findAll().stream().map(LivroDTO::new).toList();
    }

    public LivroDTO getOne(long id) {
        Optional<Livro> resultado = livroRepo.findById(id);
        if(resultado.isPresent()) {
            return new LivroDTO(resultado.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado");
    }

    public LivroDTO insert(LivroDTO livro) {
        Livro newLivro = new Livro(livro);
        Livro savedLivro = livroRepo.save(newLivro);
        LivroDTO response = new LivroDTO(savedLivro);
        return response;
    }

    public LivroDTO update(long id, LivroDTO livro) {
        Optional<Livro> resultado = livroRepo.findById(id);
        
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro não encontrado"
            );
        }

        resultado.get().setTitulo(livro.titulo());;
        resultado.get().setGeneros(livro.generos());
        resultado.get().setAutores(livro.autores());

        return new LivroDTO(livroRepo.save(resultado.get()));
    }

    public void delete(long id) {
        if(!livroRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro não encontrado"
            );
        }
        livroRepo.deleteById(id);
    }
    
}
