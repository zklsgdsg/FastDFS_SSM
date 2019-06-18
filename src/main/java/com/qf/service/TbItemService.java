package com.qf.service;

import com.qf.pojo.TbItem;

import java.util.List;

public interface TbItemService {

    boolean save(TbItem tbItem);

    List<TbItem> findAll();
}
