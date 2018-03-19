package edu.sctu.graduation.controller;

//@Controller
//public class IndexController {
//    @RequestMapping
//    @ResponseBody
//    public String index(HttpServletRequest req) {
//        System.out.println("fsfterqweqrre");
//        return "hello,world";
//    }
//}


import org.springframework.web.bind.annotation.*;

@RestController
public class IndexController {
    @RequestMapping(value = "/haha/{fsdfadf}",method = RequestMethod.POST)
    public String index(@PathVariable  String username) {
        System.out.println("haha");
        return username;
    }
}
