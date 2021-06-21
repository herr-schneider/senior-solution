package employeesspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //@Autowired kell, ha nincs Contructor injection, ne használjuk
    private HelloService helloService;

    @Autowired //autowired nem kell
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/")
    @ResponseBody  //RestController eseten nem kell
    public String sayHello() {
        return helloService.sayHello();
    }
}
