package br.biblioteca.livros.converter;

import br.biblioteca.livros.dto.BooksDTO;
import br.biblioteca.livros.models.Autor;
import br.biblioteca.livros.models.Livro;
import br.biblioteca.livros.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookConverter {
    private static AuthorsService authorsServiceStatic;
    @Autowired
    private AuthorsService authorsService;

    @PostConstruct
    public void init() {
        BookConverter.authorsServiceStatic = authorsService;
    }

    public static BooksDTO toDTO(Livro book) {
        BooksDTO dto = new BooksDTO();

        dto.setTitle(book.getName());
        dto.setPages(book.getQtdPages());

        if (book.getAuthor() != null) {
            dto.setAuthorId(book.getAuthor().getId());
            dto.setAuthor(book.getAuthor().getName());
        }

        return dto;
    }

    public static List<BooksDTO> toDTO(List<Livro> books) {
        return books.stream().map(l -> toDTO(l)).collect(Collectors.toList());
    }

    public static Livro toModel(BooksDTO book) {
        Autor author = authorsServiceStatic.searchAuthor(book.getAuthorId());

        Livro bookConverted = new Livro();
        bookConverted.setName(book.getTitle());
        bookConverted.setQtdPages(book.getPages());
        bookConverted.setAuthor(author);

        return bookConverted;
    }
}
