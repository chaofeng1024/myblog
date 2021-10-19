package org.blog.service.impl;

import org.blog.service.IndexService;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {
    @Override
    public String getIndexInfo() {
        return "this is my blog";
    }
}
