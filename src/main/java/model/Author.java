package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_name", length = 30)
    private String name;

    @Column(name = "author_surname", length = 30, unique = true)
    private String surname;

    @Column(name = "birth_date", length = 15, unique = true)
    private LocalDate birthDate;

    @Column(name = "date_of_death")
    private LocalDate deathDate;
    @Column(name= "nationality" )
    private String nationality;


    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;


}
