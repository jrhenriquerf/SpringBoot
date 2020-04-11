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
}
