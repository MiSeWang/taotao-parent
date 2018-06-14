package com.mrlv.taotao.search.controller;

import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.search.service.IItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 索引库维护
 */
@Controller
@RequestMapping("/manager")
public class ItemController {

    @Resource
    private IItemService itemService;

    /**
     * 导入商品入索引库
     */
    @RequestMapping("/importall")
    @ResponseBody
    public TaotaoResult importAllItem(){
        TaotaoResult result  = itemService.importAllItem();
        return result;
    }

}
