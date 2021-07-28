package library;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    public List<AuthorDto> listAllAuthors() {
        return service.listAllAuthors();
    }

    @GetMapping("/books")
    public List<BookDto> listAllBooks() {
        return service.listAllBooks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createAuthor(@RequestBody CreateAuthorCommand command) {
        return service.createAuthor(command);
    }

    @PutMapping("/{id}/books")
    public ResponseEntity AddBook(@PathVariable("id") long id, @RequestBody addBook command) {
        try {
            return ResponseEntity.ok(service.addBook(id, command));
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delAuthor(@PathVariable("id") Long id){
        service.delAuthor(id);
    }

    @DeleteMapping("/all")
    public void delAllAuthor(){
        service.delAllAuthor();
    }
}
