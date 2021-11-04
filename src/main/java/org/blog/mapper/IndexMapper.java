package org.blog.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.blog.entity.IndexInfo;

import java.util.List;

@Mapper
public interface IndexMapper extends BaseMapper<IndexInfo> {
    //添加地址
    List<IndexInfo> getInfo();
}
