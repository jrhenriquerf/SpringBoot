package br.biblioteca.livros.services;

import br.biblioteca.livros.exception.BookNotFoundException;
import br.biblioteca.livros.models.Book;
import br.biblioteca.livros.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> listAllBooks() {
        return bookRepository.listaLivros();
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public Book searchBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException());
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
