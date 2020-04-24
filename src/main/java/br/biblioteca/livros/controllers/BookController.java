package br.biblioteca.livros.controllers;

import br.biblioteca.livros.models.Author;
import br.biblioteca.livros.models.Book;
import br.biblioteca.livros.services.AuthorsService;
import br.biblioteca.livros.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

        List<Book> booksList = booksService.listAllBooks();
        modelAndView.addObject("books", booksList);

        return  modelAndView;
    }

    @GetMapping("/novo")
    public ModelAndView newBook(@ModelAttribute("book") Book book) {
        ModelAndView modelAndView = new ModelAndView("books/form");

        List<Author> authorsList = authorsService.listAllAuthors();
        modelAndView.addObject("authors", authorsList);

        return modelAndView;
    }

    @PostMapping(value = "/gravar")
    public ModelAndView save(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<Author> authors = authorsService.listAllAuthors();
            return new ModelAndView("books/form", "authors", authors);
        }
        booksService.saveBook(book);
        return new ModelAndView("redirect:/books/list");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Book book = booksService.searchBook(id);
        List<Author> authorsList = authorsService.listAllAuthors();

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
