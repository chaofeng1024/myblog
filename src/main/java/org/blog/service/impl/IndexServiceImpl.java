package org.blog.service.impl;

import org.blog.entity.IndexInfo;
import org.blog.mapper.IndexMapper;
import org.blog.service.IndexService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional//开启事务
public class IndexServiceImpl implements IndexService {

    private IndexMapper indexMapper;

    public IndexServiceImpl(IndexMapper indexMapper){
        this.indexMapper=indexMapper;
    }

    @Override
    public String getIndexInfo() {

        List<IndexInfo> infos = indexMapper.getInfo();
        System.out.println("Servicel");
        if(infos.get(0)!=null){
            IndexInfo indexInfo = infos.get(0);
            return indexInfo.getInfo();
        }else {
            return "不存在";
        }
    }
}
