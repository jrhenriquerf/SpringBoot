package br.biblioteca.livros.services;

import br.biblioteca.livros.models.Livro;
import br.biblioteca.livros.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    @Autowired
    BookRepository bookRepository;

    public List<Livro> listAllBooks() {
        return bookRepository.listaLivros();
    }

    public void saveBook(Livro book) {
        bookRepository.save(book);
    }

    public Livro searchBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
