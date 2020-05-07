package br.biblioteca.livros.api;

import br.biblioteca.livros.converter.BookConverter;
import br.biblioteca.livros.dto.BooksDTO;
import br.biblioteca.livros.facade.BookFacade;
import br.biblioteca.livros.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookApiController {

    @Autowired
    BookFacade bookFacade;

    @GetMapping("/book")
    public ResponseEntity<List<BooksDTO>> books() {
        List<Book> booksList = bookFacade.listAllBooks();

        return ResponseEntity.ok(BookConverter.toDTO(booksList));
    }

    @PostMapping(value = "/book")
    public ResponseEntity<Integer> store(@RequestBody BooksDTO book) {
        bookFacade.saveBook(book);

        return ResponseEntity.ok(1);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BooksDTO> find(@PathVariable("id") Long bookId) {
        try {
            Book book = bookFacade.searchBook(bookId);

            return ResponseEntity.ok(BookConverter.toDTO(book));
        } catch (Exception err) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Integer> update(@PathVariable("id") Long bookId, @RequestBody BooksDTO booksDTO) {
        try {
            bookFacade.update(bookId, booksDTO);

            return ResponseEntity.ok(1);
        } catch (Exception err) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long bookId) {
        try {
            bookFacade.deleteBook(bookId);

            return ResponseEntity.ok().build();
        } catch (Exception err) {
            return ResponseEntity.notFound().build();
        }
    }
}
