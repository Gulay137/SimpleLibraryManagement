package model;

import jakarta.persistence.*;

@Entity
public class BooksAuthors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueID;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
