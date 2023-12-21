package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String contact;

    @OneToMany(mappedBy = "library")
    private List<Book> books = new ArrayList<>();

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book, int numberOfCopies) {
        IntStream.range(0, numberOfCopies)
                .forEach(i -> {
                    books.add(book);
                });

    }
}