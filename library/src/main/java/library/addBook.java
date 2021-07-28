package library;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class addBook {

    @Schema(description = "add a book", defaultValue = "John Doe")
    private String ISBN;

    private String title;
}
