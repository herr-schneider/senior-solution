package employeesspringjpa;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Table(name = "Alkalmazott")

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  //  @Column(name = "Alk_nev", length = 200, nullable = false)
  //  @Name
    private String name;

//    @Enumerated(EnumType.STRING)
//    private EmployeeType employeeType = EmployeeType.FULL_TIME;
//
//    @ElementCollection
//    private List<String> nicknames;

    public Employee(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public EmployeeType getEmployeeType() {
//        return employeeType;
//    }
//
//    public void setEmployeeType(EmployeeType employeeType) {
//        this.employeeType = employeeType;
//    }
//
//    public List<String> getNicknames() {
//        return nicknames;
//    }
//
//    public void setNicknames(List<String> nicknames) {
//        this.nicknames = nicknames;
//    }
}
