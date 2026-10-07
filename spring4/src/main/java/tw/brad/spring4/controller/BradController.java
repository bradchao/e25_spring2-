package tw.brad.spring4.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.brad.spring4.annotation.BradAop;

@RequestMapping("/brad")
@RestController
public class BradController {

    @RequestMapping("/test1")
    public void test1(){
        System.out.println("brad:test1()");
    }

    @BradAop
    @RequestMapping("/test2")
    public void test2(){
        System.out.println("brad:test2()");
    }

    @BradAop
    @RequestMapping("/test3")
    public void test3(){
        System.out.println("brad:test3()");
    }

}
