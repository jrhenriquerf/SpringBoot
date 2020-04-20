package br.biblioteca.livros.api;

import br.biblioteca.livros.converter.BookConverter;
import br.biblioteca.livros.dto.BooksDTO;
import br.biblioteca.livros.models.Livro;
import br.biblioteca.livros.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookApiController {

    @Autowired
    BooksService booksService;

    @GetMapping("/book")
    public ResponseEntity<List<BooksDTO>> books() {
        List<Livro> booksList = booksService.listAllBooks();

        return ResponseEntity.ok(BookConverter.toDTO(booksList));
    }

    @PostMapping(value = "/book")
    public ResponseEntity<Integer> store(@RequestBody BooksDTO book) {
        Livro bookConverted = BookConverter.toModel(book);

        booksService.saveBook(bookConverted);

        return ResponseEntity.ok(1);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BooksDTO> find(@PathVariable("id") Long bookId) {
        try {
            Livro book = booksService.searchBook(bookId);

            return ResponseEntity.ok(BookConverter.toDTO(book));
        } catch (Exception err) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Integer> update(@PathVariable("id") Long bookId, @RequestBody BooksDTO booksDTO) {
        try {
            Livro book = booksService.searchBook(bookId);

            Livro bookConverted = BookConverter.toModel(booksDTO);

            bookConverted.setId(book.getId());

            booksService.saveBook(bookConverted);

            return ResponseEntity.ok(1);
        } catch (Exception err) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long bookId) {
        try {
            booksService.deleteBook(bookId);

            return ResponseEntity.ok().build();
        } catch (Exception err) {
            return ResponseEntity.notFound().build();
        }
    }
}
