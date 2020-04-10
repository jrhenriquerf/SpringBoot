package br.biblioteca.livros.models;

import javax.persistence.*;

@Entity
@Table(name = "LIVRO")
public class Livro {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "NOME", nullable = false)
    private String name;

    @Column(name = "quantidadePaginas")
    private int qtdPages;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "AUTHOR_ID")
    private Autor author;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQtdPages() {
        return qtdPages;
    }

    public void setQtdPages(int qtdPages) {
        this.qtdPages = qtdPages;
    }

    public Autor getAuthor() {
        return author;
    }

    public void setAuthor(Autor author) {
        this.author = author;
    }
}
