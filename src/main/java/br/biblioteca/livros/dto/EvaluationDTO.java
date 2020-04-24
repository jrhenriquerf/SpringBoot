package br.biblioteca.livros.dto;

public class EvaluationDTO {

    private String comment;

    private int note;

    public String getComment() {
        return comment;
    }

    public int getNote() {
        return note;
    }

    public void setComment(String comentario) {
        this.comment = comentario;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
