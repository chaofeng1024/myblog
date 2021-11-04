package org.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.blog.entity.Goods;

import java.util.List;

@Mapper
public interface GoodMapper extends BaseMapper<Goods> {

    List<Goods> getGoodsByIdList(List<Integer> IdList);


}
