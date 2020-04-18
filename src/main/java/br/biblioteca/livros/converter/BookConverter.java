package br.biblioteca.livros.converter;

import br.biblioteca.livros.dto.BooksDTO;
import br.biblioteca.livros.models.Livro;

import java.util.List;
import java.util.stream.Collectors;

public class BookConverter {

    public static BooksDTO toDTO(Livro book) {
        BooksDTO dto = new BooksDTO();

        dto.setTitle(book.getName());
        dto.setPages(book.getQtdPages());
        dto.setAuthor(book.getAuthor() != null ? book.getAuthor().getName() : null);

        return dto;
    }

    public static List<BooksDTO> toDTO(List<Livro> books) {
        return books.stream().map(l -> toDTO(l)).collect(Collectors.toList());
    }

}
