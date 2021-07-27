package library;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ISBN;

    private String title;

    @ManyToOne
    private Author author;

    public Book(String ISBN, String title) {
        this.ISBN = ISBN;
        this.title = title;
    }
}
