package br.biblioteca.livros.controllers;

import br.biblioteca.livros.models.Autor;
import br.biblioteca.livros.models.Livro;
import br.biblioteca.livros.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    public ModelAndView newAuthor(@ModelAttribute("author") Autor author) {
        ModelAndView modelAndView = new ModelAndView("authors/form");

        return modelAndView;
    }

    @PostMapping("/gravar")
    public ModelAndView save(@Valid @ModelAttribute("author") Autor author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("authors/form");
        }
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
