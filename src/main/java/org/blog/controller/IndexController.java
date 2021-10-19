package org.blog.controller;

import org.blog.service.IndexService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")//请求跨越
@RestController //类上加了@RestController注解，等价于在类中的每个方法上都加了@ResponseBody
@RequestMapping("/index")
public class IndexController {

    private IndexService indexService;

    public IndexController(IndexService indexService){
        this.indexService=indexService;
    }

    @GetMapping("/getIndexInfo")
    public Object getIndexInfo(){
        System.out.println("get index info");
        return indexService.getIndexInfo();
    }

}
