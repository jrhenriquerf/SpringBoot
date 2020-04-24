package br.biblioteca.livros.services;

import br.biblioteca.livros.models.Author;
import br.biblioteca.livros.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorsService {
    @Autowired
    AuthorRepository authorRepository;

    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    public Author searchAuthor(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
