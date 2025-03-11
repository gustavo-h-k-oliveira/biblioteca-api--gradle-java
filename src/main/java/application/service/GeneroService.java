package application.service;

import application.model.Genero;
import application.record.GeneroDTO;
import application.repository.GeneroRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepo;

    public Iterable<GeneroDTO> getAll() {
        return generoRepo.findAll().stream().map(GeneroDTO::new).toList();
    }

    public GeneroDTO getOne(long id) {
        Optional<Genero> resultado = generoRepo.findById(id);
        if(resultado.isPresent()) {
            return new GeneroDTO(resultado.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gênero não encontrado");
    }

    public GeneroDTO insert(GeneroDTO genero) {
        Genero newGenero = new Genero(genero);
        Genero savedGenero = generoRepo.save(newGenero);
        GeneroDTO response = new GeneroDTO(savedGenero);
        return response;
    }

    public GeneroDTO update(long id, GeneroDTO genero) {
        Optional<Genero> resultado = generoRepo.findById(id);
        
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Gênero não encontrado"
            );
        }

        resultado.get().setDescricao(genero.descricao());

        return new GeneroDTO(generoRepo.save(resultado.get()));
    }

    public void delete(long id) {
        if(!generoRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Gênero não encontrado"
            );
        }
        generoRepo.deleteById(id);
    }

}
