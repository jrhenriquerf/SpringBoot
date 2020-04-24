package br.biblioteca.livros.models;

import javax.persistence.*;

@Entity
@Table(name = "AVALIACAO")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COMENTARIO", nullable = false)
    private String comment;

    @Column(name = "NOTA")
    private int note;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public int getNote() {
        return note;
    }

    public Book getBook() {
        return book;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
