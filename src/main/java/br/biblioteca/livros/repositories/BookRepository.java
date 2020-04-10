package br.biblioteca.livros.repositories;

import br.biblioteca.livros.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Livro, Long> {

}