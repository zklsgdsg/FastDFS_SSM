package com.qf.service.impl;

import com.qf.mapper.TbItemMapper;
import com.qf.pojo.TbItem;
import com.qf.service.TbItemService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TbItemServiceImpl implements TbItemService {

    @Resource
    private TbItemMapper tbItemMapper;

    public boolean save(TbItem tbItem) {
        int i = tbItemMapper.saveItem(tbItem);

        return i>0;
    }

    public List<TbItem> findAll() {
        return tbItemMapper.findAll();
    }
}
