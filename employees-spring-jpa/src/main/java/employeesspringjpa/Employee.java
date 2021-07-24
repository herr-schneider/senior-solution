package employeesspringjpa;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Set;

enum EmployeeType {
    QUAD_TIME, HALF_TIME, FULL_TIME;
}

//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "Alkalmazott")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  //  @Column(name = "Alk_nev", length = 200, nullable = false)
  //  @Name
    private String name;

//    @Enumerated(EnumType.STRING)
//    private EmployeeType employeeType = EmployeeType.FULL_TIME;

    @ElementCollection
    @Cascade(CascadeType.REMOVE)
    private Set<String> nicknames;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, Set<String> nicknames) {
        this.name = name;
        this.nicknames = nicknames;
    }

    public Employee(Long id, String name, Set<String> nicknames) {
        this.id = id;
        this.name = name;
        this.nicknames = nicknames;
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

//    public EmployeeType getEmployeeType() {
//        return employeeType;
//    }
//
//    public void setEmployeeType(EmployeeType employeeType) {
//        this.employeeType = employeeType;
//    }

    public Set<String> getNicknames() {
        return nicknames;
    }

    public void addNickName(String nickname){
        nicknames.add(nickname);
    }

    public void setNicknames(Set<String> nicknames) {
        this.nicknames = nicknames;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'';
    }
}
