package br.biblioteca.livros.controllers;

import br.biblioteca.livros.models.Livro;
import br.biblioteca.livros.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BooksService booksService;

    @GetMapping("/list")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("books/list");

        List<Livro> booksList = booksService.listAllBooks();
        modelAndView.addObject("livros", booksList);

        return  modelAndView;
    }

    @GetMapping("/novo")
    public ModelAndView newBook() {
        return new ModelAndView("books/new");
    }

    @PostMapping("/gravar")
    public ModelAndView save(String livro) {
        return new ModelAndView("redirect:/books/list");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        return new ModelAndView("books/edit");
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        return new ModelAndView("redirect:/books/list");
    }
}
