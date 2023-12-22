package model;

;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(unique = true)
    private String ISBN;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
