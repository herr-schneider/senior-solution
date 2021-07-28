package library;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "A nice Library")
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
    @Operation(summary = "Author Creater", description = "Create a author.")
    public String createAuthor(@Parameter(description = "Author's name", example = "V.I.Lenin")
                                   @RequestBody CreateAuthorCommand command) {
        return service.createAuthor(command);
    }

    @PutMapping("/{id}/books")
    @ApiResponse(responseCode = "404", description = "Book not found")
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