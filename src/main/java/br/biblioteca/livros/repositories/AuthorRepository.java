package br.biblioteca.livros.repositories;

import br.biblioteca.livros.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Autor, Long> {

}