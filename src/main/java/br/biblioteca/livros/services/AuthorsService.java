package br.biblioteca.livros.services;

import br.biblioteca.livros.models.Autor;
import br.biblioteca.livros.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorsService {
    @Autowired
    AuthorRepository authorRepository;

    public List<Autor> listAllAuthors() {
        return authorRepository.findAll();
    }
}
