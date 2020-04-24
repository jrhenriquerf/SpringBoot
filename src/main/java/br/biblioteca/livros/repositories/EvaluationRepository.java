package br.biblioteca.livros.repositories;

import br.biblioteca.livros.models.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
}
