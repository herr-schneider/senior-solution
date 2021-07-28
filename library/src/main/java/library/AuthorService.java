package library;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@org.springframework.stereotype.Service
public class AuthorService {

    private AuthorRepo authorRepo;

    private BookRepo bookRepo;

    private ModelMapper modelMapper;


    public List<AuthorDto> listAllAuthors() {
        return authorRepo.findAll().stream()
                .map(a -> modelMapper.map(a, AuthorDto.class))
                .collect(Collectors.toList());
    }

    public List<BookDto> listAllBooks() {
        return bookRepo.findAll().stream()
                .map(a -> modelMapper.map(a, BookDto.class))
                .collect(Collectors.toList());
    }

    public String createAuthor(CreateAuthorCommand command) {
        Author author = new Author(command.getName());
        authorRepo.save(author);
        return author.getName();
    }


    @Transactional
    public AuthorDto addBook(long id, addBook command) {
        Author author = authorRepo.findById(id).orElseThrow(()-> new IllegalArgumentException()); //??authorRepo.getById(id);
        author.addBook(new Book(command.getISBN(), command.getTitle()));
        return modelMapper.map(author, AuthorDto.class);
    }

    public void delAuthor(Long id) {
        authorRepo.deleteById(id);
    }

    public void delAllAuthor() {
        authorRepo.deleteAll();
    }
}
