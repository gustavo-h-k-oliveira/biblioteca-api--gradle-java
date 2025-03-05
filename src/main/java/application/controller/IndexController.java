package application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class IndexController {
    private List<String> livros = new ArrayList<String>();
    
    /*
    - GET
    - GET/{id}
    - POST
    - PUT/{id}
    - DELETE/{id}
    */

    @GetMapping
    public List<String> home() {
        return livros;
    }

    @PostMapping
    public List<String> homePost(@RequestBody String livro) {
        livros.add(livro);
        return livros;
    }
}
