package br.biblioteca.livros.dto;

import java.util.ArrayList;
import java.util.List;

public class BooksDTO {
    private String title;
    private int pages;
    private Long authorId;
    private String author;
    private List<EvaluationDTO> evaluations = new ArrayList<>();

    public int getPages() {
        return pages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public List<EvaluationDTO> getEvaluations() {
        return evaluations;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setEvaluations(List<EvaluationDTO> evaluations) {
        this.evaluations = evaluations;
    }
}
