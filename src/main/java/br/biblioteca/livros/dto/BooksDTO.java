package br.biblioteca.livros.dto;

public class BooksDTO {
    private String title;
    private int pages;
    private Long authorId;
    private String author;

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
}
