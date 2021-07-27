package library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = {javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.REMOVE}, mappedBy = "author")
    private List<Book> books;

    public Author(String name) {
        this.name = name;
    }

    public void addBook(Book book){
        if (books == null){
           books = new ArrayList<>();
        }
        books.add(book);
        book.setAuthor(this);
    }
}
