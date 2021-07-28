package library;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Book", description = "Add a book")
public class addBook {


    @Schema(description = "International Standard Book Number")
    private String ISBN;

    @NotNull
    @Schema(description="add a book", example = "War and Peace")
    private String title;
}
