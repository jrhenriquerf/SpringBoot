package br.biblioteca.livros.api;

import br.biblioteca.livros.dto.EvaluationDTO;
import br.biblioteca.livros.exception.BookNotFoundException;
import br.biblioteca.livros.facade.ApiFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookEvaluationApiController {

    @Autowired
    ApiFacade apiFacade;

    @PostMapping("/book/evaluation/{id}")
    public ResponseEntity<Long> createComment(@PathVariable("id") Long id, @RequestBody EvaluationDTO evaluationDTO) {
        try {
            return ResponseEntity.ok(apiFacade.saveEvaluation(id, evaluationDTO));
        } catch (BookNotFoundException err) {
            return ResponseEntity.notFound().build();
        }
    }
}
