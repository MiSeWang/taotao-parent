package com.mrlv.taotao.search.service.impl;

import com.mrlv.taotao.common.pojo.TaotaoResult;
import com.mrlv.taotao.search.mapper.ItemSearchMapper;
import com.mrlv.taotao.search.pojo.Item;
import com.mrlv.taotao.search.service.IItemService;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements IItemService{

    @Resource
    private ItemSearchMapper itemSearchMapper;

    @Resource
    private HttpSolrClient httpSolrClient;

    @Override
    public TaotaoResult importAllItem() {
        httpSolrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/taotao_core").build();
        //查询商品列表
        List<Item> itemList = itemSearchMapper.getItemList();
        //将商品信息写入索引库
        try {
            for (Item item : itemList){
                //创建一个SolrInputDocument
                SolrInputDocument document = new SolrInputDocument();
                document.setField("id", item.getId());
                document.setField("item_title", item.getTitle());
                document.setField("item_sell_point", item.getSell_point());
                document.setField("item_price", item.getPrice());
                document.setField("item_image", item.getImage());
                document.setField("item_category_name", item.getCategory_name());
                //一旦出现不存在的索引字段，则无法创建索引
//              document.setField("item_desc", item.getItem_des());
                httpSolrClient.add(document);
            }
            httpSolrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TaotaoResult.ok();
    }
}
