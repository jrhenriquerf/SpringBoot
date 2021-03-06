package br.biblioteca.livros.repositories;

import br.biblioteca.livros.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = " from #{#entityName} l left join fetch l.author a order by l.name desc ")
    List<Book> listaLivros();
}