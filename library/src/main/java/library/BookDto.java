package library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {


    private String ISBN;

    private String title;

    // Author ki kell venni, mert végtelen ciklusba kerül

}
