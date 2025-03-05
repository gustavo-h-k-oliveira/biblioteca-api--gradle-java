package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import application.model.Livro;
import application.repository.LivroRepository;

@RestController
@RequestMapping("/livros")
public class LivrosController {
    @Autowired
    private LivroRepository livroRepo;

    @GetMapping
    public Iterable<Livro> list() {
        return livroRepo.findAll();
    }

    @PostMapping
    public Livro insert(@RequestBody Livro novoLivro) {
        return livroRepo.save(novoLivro);
    }

    @PutMapping("/{id}")
    public Livro update(@RequestBody Livro dados, @PathVariable long id) {
        Optional<Livro> resultado = livroRepo.findById(id);
        if(resultado.isPresent()) {
            resultado.get().setTitulo(dados.getTitulo());
            return livroRepo.save(resultado.get());
        }
        return new Livro();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        livroRepo.deleteById(id);
    }
}
