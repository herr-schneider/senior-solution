package employeesspring;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HelloService {

    public String sayHello() {
        return "<h1>Hello</h2> <p>Service</p>" + LocalDateTime.now();
    }
}
