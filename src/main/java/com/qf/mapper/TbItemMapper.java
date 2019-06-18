package com.qf.mapper;

import com.qf.pojo.TbItem;

import java.util.List;

public interface TbItemMapper {

    //添加商品信息
    int saveItem(TbItem tbItem);

    //查询所有商品信息
    List<TbItem> findAll();
}
