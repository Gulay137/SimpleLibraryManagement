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

    @Column(length = 100)
    private String name;

    @Column(nullable = true)
    private String address;

    @Column(name = "contact_number")
    private String contact;

    // New column
    private int yearFounded;

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
