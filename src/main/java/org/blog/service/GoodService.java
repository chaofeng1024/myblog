package org.blog.service;

import org.blog.entity.Goods;

import java.util.List;

public interface GoodService {
    List<Goods> selectAllGoods();

    List<Goods> findGoodsByIdList(List<Integer> IDList);
}
