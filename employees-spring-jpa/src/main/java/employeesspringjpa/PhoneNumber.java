package employeesspringjpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "phone_numbers")
@NoArgsConstructor
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int pos;

    private String type;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    public PhoneNumber(String type, String phoneNumber) {
        this.type = type;
        this.phoneNumber = phoneNumber;
    }
}
