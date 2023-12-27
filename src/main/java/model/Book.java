package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books", schema = "library_management")
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "ISBN", unique = true, length = 17)
    private String ISBN;

    @Column(name = "publication_year", length = 4)
    private String publicationYear;

    private String description;
    private String language;

    @ManyToOne
    private Author author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "library_management",
            name = "books_libraries",
            joinColumns = @JoinColumn(name = "books_id"),
            inverseJoinColumns = @JoinColumn(name = "libraries_id"))
    private List<Library> libraries = new ArrayList<>();

    public int getAvailableCopies() {
        return libraries.stream()
                .mapToInt(library -> library.countCopiesOfBook(this))
                .sum();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", publicationYear='" + publicationYear + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", author=" + (author != null ? author.getName() : "null") +
                '}';
    }
}
