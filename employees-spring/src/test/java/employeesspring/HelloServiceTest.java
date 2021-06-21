package employeesspring;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HelloServiceTest {

    @Test
    void testSayHello(){
        HelloService helloService = new HelloService();
        String message = helloService.sayHello();

        assertThat(message).startsWith("<h1>Hello");
    }
}