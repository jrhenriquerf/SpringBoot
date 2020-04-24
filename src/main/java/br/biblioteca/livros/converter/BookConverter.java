package br.biblioteca.livros.converter;

import br.biblioteca.livros.dto.BooksDTO;
import br.biblioteca.livros.models.Author;
import br.biblioteca.livros.models.Book;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookConverter {

    public static BooksDTO toDTO(Book book) {
        BooksDTO dto = new BooksDTO();

        dto.setTitle(book.getName());
        dto.setPages(book.getQtdPages());
        dto.setAuthorId(null);
        dto.setAuthor(null);
        dto.setEvaluations(book.getEvaluations().size() > 0
                ? EvaluationConverter.toDTO(book.getEvaluations())
                : Collections.emptyList());

        if (book.getAuthor() != null) {
            dto.setAuthorId(book.getAuthor().getId());
            dto.setAuthor(book.getAuthor().getName());
        }

        return dto;
    }

    public static List<BooksDTO> toDTO(List<Book> books) {
        return books.stream().map(l -> toDTO(l)).collect(Collectors.toList());
    }

    public static Book toModel(BooksDTO book, Author author) {
        Book bookConverted = new Book();
        bookConverted.setName(book.getTitle());
        bookConverted.setQtdPages(book.getPages());
        bookConverted.setAuthor(author);

        return bookConverted;
    }
}
