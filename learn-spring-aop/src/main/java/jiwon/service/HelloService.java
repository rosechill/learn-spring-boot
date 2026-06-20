package jiwon.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloService {

    public String hello(String name) {
        log.info("Call HelloSerivce.hello()");
        return "Hello " + name;
    }

    public String bye(String name) {
        log.info("Call HelloSerivce.bye()");
        return "Bye " + name;
    }

    public void test() {
        log.info("Call HelloSerivce.test()");
    }
}
