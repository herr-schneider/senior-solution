package musicStore;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class CreateInstrumentCommand {

    @NotBlank
    private String brand;
    private InstrumentType type;

    @PositiveOrZero
    private int price;
    private LocalDate postDate = LocalDate.now();

    public CreateInstrumentCommand(String brand, InstrumentType type, int price) {
        this.brand = brand;
        this.type = type;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public InstrumentType getType() {
        return type;
    }

    public void setType(InstrumentType type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

}
