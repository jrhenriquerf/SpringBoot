package br.biblioteca.livros.controllers;

import br.biblioteca.livros.models.Autor;
import br.biblioteca.livros.models.Livro;
import br.biblioteca.livros.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    AuthorsService authorsService;

    @GetMapping("/list")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("authors/list");

        List<Autor> authorsList = authorsService.listAllAuthors();
        modelAndView.addObject("authors", authorsList);

        return  modelAndView;
    }

    @GetMapping("/novo")
    public ModelAndView newAuthor() {
        ModelAndView modelAndView = new ModelAndView("authors/form");

        modelAndView.addObject("author", new Autor());

        return modelAndView;
    }

    @PostMapping("/gravar")
    public ModelAndView save(Autor author) {
        authorsService.saveAuthor(author);
        return new ModelAndView("redirect:/authors/list");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Autor author = authorsService.searchAuthor(id);

        ModelAndView modelAndView = new ModelAndView("authors/form");
        modelAndView.addObject("author", author);

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        authorsService.deleteAuthor(id);
        return new ModelAndView("redirect:/authors/list");
    }
}
