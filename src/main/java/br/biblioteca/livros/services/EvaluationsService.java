package br.biblioteca.livros.services;

import br.biblioteca.livros.models.Evaluation;
import br.biblioteca.livros.repositories.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluationsService {

    @Autowired
    EvaluationRepository evaluationRepository;

    public Long saveEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation).getId();
    }
}
