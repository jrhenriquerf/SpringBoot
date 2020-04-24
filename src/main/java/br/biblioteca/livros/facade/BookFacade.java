package br.biblioteca.livros.facade;

import br.biblioteca.livros.converter.BookConverter;
import br.biblioteca.livros.dto.BooksDTO;
import br.biblioteca.livros.models.Author;
import br.biblioteca.livros.models.Book;
import br.biblioteca.livros.services.AuthorsService;
import br.biblioteca.livros.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookFacade {

    @Autowired
    BooksService booksService;

    @Autowired
    AuthorsService authorsService;

    public List<Book> listAllBooks() {
        return booksService.listAllBooks();
    }

    public void saveBook(BooksDTO booksDTO) {
        Author author = null;

        if (booksDTO.getAuthorId() != null) {
            author = authorsService.searchAuthor(booksDTO.getAuthorId());
        }

        Book bookConverted = BookConverter.toModel(booksDTO, author);

        booksService.saveBook(bookConverted);
    }

    public Book searchBook(Long bookId) {
        return booksService.searchBook(bookId);
    }

    public void update(Long bookId, BooksDTO booksDTO) {
        Book book = booksService.searchBook(bookId);

        Author author = null;

        if (booksDTO.getAuthorId() != null) {
            author = authorsService.searchAuthor(booksDTO.getAuthorId());
        }

        Book bookConverted = BookConverter.toModel(booksDTO, author);

        bookConverted.setId(book.getId());

        booksService.saveBook(bookConverted);
    }

    public void deleteBook(Long bookId) {
        booksService.deleteBook(bookId);
    }
}
