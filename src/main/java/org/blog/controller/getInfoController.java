package org.blog.controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")//请求跨越
@RestController //类上加了@RestController注解，等价于在类中的每个方法上都加了@ResponseBody
@RequestMapping("/get")
public class getInfoController {
    @GetMapping("/info")
    public String getInfo(){
        return "123";
    }
}
