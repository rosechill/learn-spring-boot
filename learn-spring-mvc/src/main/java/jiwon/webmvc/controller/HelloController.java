package jiwon.webmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jiwon.webmvc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

//    request mapping
//    @RequestMapping(path = "/hello", method = RequestMethod.GET)
//    public void helloWorld(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String name = request.getParameter("name");
//        String responseBody = helloService.hello(name);
//        response.getWriter().println(responseBody);
//    }

//    getMapping
//    @GetMapping(path = "/hello")
//    public void helloWorld(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String name = request.getParameter("name");
//        String responseBody = helloService.hello(name);
//        response.getWriter().println(responseBody);
//    }

    // request param
    @GetMapping(path = "/hello")
    public void helloWorld(@RequestParam(name = "name", required = false) String name,
                           HttpServletResponse response) throws IOException {
        String responseBody = helloService.hello(name);
        response.getWriter().println(responseBody);
    }


}
