package employeesspringjpa;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


//@NoArgsConstructor
//@AllArgsConstructor
//@NamedQuery(name = "nameLike", query = "select e from Employee e join fetch e.nicknames where e.name like :name")
//@Table(name = "Alkalmazott")
@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  @Column(name = "Alk_nev", length = 200, nullable = false)
    //  @Name
    private String name;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType = EmployeeType.FULL_TIME;

//    @ElementCollection
//    @OneToOne(cascade = javax.persistence.CascadeType.REMOVE)
//    private Set<String> nicknames;

    @OneToOne
    private ParkingPlace parkingPlace;

    //@OrderBy("type")
    //@OrderColumn(name = "pos")
    @OneToMany(cascade = {javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.REMOVE}, mappedBy = "employee")
    private List<PhoneNumber> phoneNumbers;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

//    public Employee(String name, Set<String> nicknames) {
//        this.name = name;
//        this.nicknames = nicknames;
//    }
//
//    public Employee(Long id, String name, Set<String> nicknames) {
//        this.id = id;
//        this.name = name;
//        this.nicknames = nicknames;
//    }
public void addPhoneNumber(PhoneNumber phoneNumber){
        if (phoneNumbers == null){
            phoneNumbers = new ArrayList<>();
        }
        phoneNumbers.add(phoneNumber);
        phoneNumber.setEmployee(this);
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

//    public Set<String> getNicknames() {
//        return nicknames;
//    }
//
//    public void addNickName(String nickname) {
//        nicknames.add(nickname);
//    }
//
//    public void setNicknames(Set<String> nicknames) {
//        this.nicknames = nicknames;
//    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'';
    }
}
