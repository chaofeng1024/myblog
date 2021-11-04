package org.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.blog.entity.Goods;
import org.blog.exception.BizException;
import org.blog.mapper.GoodMapper;
import org.blog.service.GoodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    private GoodMapper goodMapper;

    public GoodServiceImpl(GoodMapper goodMapper) {
        this.goodMapper = goodMapper;
    }

    @Override
    public List<Goods> selectAllGoods() {
        List<Goods> goods;
        try {
            goods = goodMapper.selectList(Wrappers.emptyWrapper());
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException("业务层查询异常"+e.getMessage());
        }

        return goods;
    }

    @Override
    public List<Goods> findGoodsByIdList(List<Integer> IDList) {

        List<Goods> goodsByIdList = goodMapper.getGoodsByIdList(IDList);

        return goodsByIdList;
    }
}
