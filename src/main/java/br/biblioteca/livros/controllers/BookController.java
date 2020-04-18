package br.biblioteca.livros.controllers;

import br.biblioteca.livros.models.Autor;
import br.biblioteca.livros.models.Livro;
import br.biblioteca.livros.services.AuthorsService;
import br.biblioteca.livros.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BooksService booksService;

    @Autowired
    AuthorsService authorsService;

    @GetMapping("/list")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("books/list");

        List<Livro> booksList = booksService.listAllBooks();
        modelAndView.addObject("books", booksList);

        return  modelAndView;
    }

    @GetMapping("/novo")
    public ModelAndView newBook(@ModelAttribute("book") Livro book) {
        ModelAndView modelAndView = new ModelAndView("books/form");

        List<Autor> authorsList = authorsService.listAllAuthors();
        modelAndView.addObject("authors", authorsList);

        return modelAndView;
    }

    @PostMapping(value = "/gravar")
    public ModelAndView save(@Valid Livro book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new     ModelAndView("books/form");

            List<Autor> authorsList = authorsService.listAllAuthors();
            modelAndView.addObject("authors", authorsList);
            modelAndView.addObject("book", book);

            return modelAndView;
        }
        booksService.saveBook(book);
        return new ModelAndView("redirect:/books/list");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Livro book = booksService.searchBook(id);
        List<Autor> authorsList = authorsService.listAllAuthors();

        ModelAndView modelAndView = new ModelAndView("books/form");
        modelAndView.addObject("authors", authorsList);
        modelAndView.addObject("book", book);

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        booksService.deleteBook(id);
        return new ModelAndView("redirect:/books/list");
    }
}
