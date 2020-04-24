package br.biblioteca.livros.facade;

import br.biblioteca.livros.converter.EvaluationConverter;
import br.biblioteca.livros.dto.EvaluationDTO;
import br.biblioteca.livros.models.Book;
import br.biblioteca.livros.services.BooksService;
import br.biblioteca.livros.services.EvaluationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiFacade {

    @Autowired
    BooksService booksService;

    @Autowired
    EvaluationsService evaluationsService;

    public List<Book> listAllBooks() {
        return booksService.listAllBooks();
    }

    public Long saveEvaluation(Long bookId, EvaluationDTO evaluationDTO) {
        Book book = booksService.searchBook(bookId);
        return evaluationsService.saveEvaluation(EvaluationConverter.toModel(evaluationDTO, book));
    }
}
