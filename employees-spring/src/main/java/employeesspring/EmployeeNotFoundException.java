package employeesspring;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(String massage){
        super(massage);
    }

    public EmployeeNotFoundException(String massage, Exception e){
        super(massage, e);
    }
}
