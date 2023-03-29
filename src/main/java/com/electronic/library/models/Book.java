package com.electronic.library.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;


@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Заполните Название")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Заполните автора")
    @Column(name = "author")
    private String author;

    @Column(name = "year_of_publication")
    private int yearOfPublication;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Book(int id, String title, String author, int yearOfPublication) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }
    public Book(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Optional<Person> getOwner() {
        return Optional.ofNullable(owner);
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
