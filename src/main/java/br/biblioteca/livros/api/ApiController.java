package br.biblioteca.livros.api;

import br.biblioteca.livros.converter.BookConverter;
import br.biblioteca.livros.dto.BooksDTO;
import br.biblioteca.livros.dto.EvaluationDTO;
import br.biblioteca.livros.exception.BookNotFoundException;
import br.biblioteca.livros.facade.ApiFacade;
import br.biblioteca.livros.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ApiFacade apiFacade;

    @GetMapping("/book/list")
    public ResponseEntity<List<BooksDTO>> books() {
        List<Book> booksList = apiFacade.listAllBooks();

        return ResponseEntity.ok(BookConverter.toDTO(booksList));
    }

    @PostMapping("/book/evaluation/{id}")
    public ResponseEntity<Long> createComment(@PathVariable("id") Long id, @RequestBody EvaluationDTO evaluationDTO) {
        try {
            return ResponseEntity.ok(apiFacade.saveEvaluation(id, evaluationDTO));
        } catch (BookNotFoundException err) {
            return ResponseEntity.notFound().build();
        }
    }
}
