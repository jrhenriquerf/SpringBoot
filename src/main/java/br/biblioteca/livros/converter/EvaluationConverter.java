package br.biblioteca.livros.converter;

import br.biblioteca.livros.dto.BooksDTO;
import br.biblioteca.livros.dto.EvaluationDTO;
import br.biblioteca.livros.models.Evaluation;
import br.biblioteca.livros.models.Book;
import groovy.util.Eval;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EvaluationConverter {

    public static Evaluation toModel(EvaluationDTO evaluationDTO) {
        Evaluation evaluationConverted = new Evaluation();
        evaluationConverted.setComment(evaluationDTO.getComment());
        evaluationConverted.setNote(evaluationDTO.getNote());

        return evaluationConverted;
    }

    public static Evaluation toModel(EvaluationDTO evaluationDTO, Book book) {
        Evaluation evaluationConverted = new Evaluation();
        evaluationConverted.setComment(evaluationDTO.getComment());
        evaluationConverted.setNote(evaluationDTO.getNote());
        evaluationConverted.setBook(book);

        return evaluationConverted;
    }

    public static EvaluationDTO toDTO(Evaluation evaluation) {
        EvaluationDTO evaluationDTO = new EvaluationDTO();
        evaluationDTO.setComment(evaluation.getComment());
        evaluationDTO.setNote(evaluation.getNote());

        return evaluationDTO;
    }

    public static List<EvaluationDTO> toDTO(List<Evaluation> evaluations) {
        return evaluations.stream().map(l -> toDTO(l)).collect(Collectors.toList());
    }
}
