package org.blog.controller;

import com.alibaba.fastjson.JSON;
import org.blog.entity.Goods;
import org.blog.entity.User;
import org.blog.exception.BizException;
import org.blog.exception.ControllerException;
import org.blog.service.GoodService;
import org.blog.utils.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@CrossOrigin("*")
@Controller
@RequestMapping("/good")
public class GoodController {

    private GoodService goodService;

    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @ResponseBody
    @GetMapping()
    public Object getAllGoods(HttpSession session){

//        模拟登陆状态，在Session中存入user
        if(session.getAttribute("user") == null || "".equals(session.getAttribute("user"))){
            User user = new User();
            user.setName("ssx");
            user.setUId(28);
            session.setAttribute("user",user);
        }

        Map<String,Object> map = null;
        try {
            List<Goods> goods = goodService.selectAllGoods();
            map = MapUtils.getSuccessMap(goods);
        }catch (BizException bizException){
            throw bizException;
        }catch (Exception e){
            e.printStackTrace();
            throw new ControllerException("控制层异常：" + e.getMessage());
        }
        return map;
    }

    @ResponseBody
    @PostMapping
    public Object selectGoodById(String IDList){
        HashMap hashMap = JSON.parseObject(IDList, HashMap.class);

        Set<String> set = hashMap.keySet();

        ArrayList<Integer> iDList = new ArrayList<>();

        Iterator<String> iterator = set.iterator();

        while (iterator.hasNext()) {
            iDList.add(Integer.valueOf(iterator.next()));
        }
        List<Goods> goodsByIdList = goodService.findGoodsByIdList(iDList);
        return goodsByIdList;

    }
}
