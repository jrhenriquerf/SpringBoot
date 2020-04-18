package br.biblioteca.livros.api;

import br.biblioteca.livros.converter.BookConverter;
import br.biblioteca.livros.dto.BooksDTO;
import br.biblioteca.livros.models.Livro;
import br.biblioteca.livros.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    BooksService booksService;

    @GetMapping("/books/list")
    public ResponseEntity<List<BooksDTO>> books() {
        List<Livro> booksList = booksService.listAllBooks();

        return ResponseEntity.ok(BookConverter.toDTO(booksList));
    }
}
