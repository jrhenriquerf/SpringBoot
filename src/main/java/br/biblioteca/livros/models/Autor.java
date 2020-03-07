package br.biblioteca.livros.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Autor {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "NOME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Livro> books = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Livro> getBooks() {
        return books;
    }

    public void setBooks(List<Livro> books) {
        this.books = books;
    }
}
