package com.mrlv.taotao.solrj;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;


public class SolrjTest {

    @Test
    public void addDocument() throws Exception{
        //创建连接
        //  1.单机版用http
        //  2.集群版用cloud
        // solr4创建方式
        //  SolrServer solrServer = new HttpSolrServer("http://127.0.0.1:8080/solr");
        // solr5创建方式,在url中指定core名称：core
        //  HttpSolrClient solrServer = new HttpSolrClient("http://127.0.0.1:8080/solr/core");
        // solr7创建方式,在url中指定core名称：core
        HttpSolrClient solrServer= new HttpSolrClient.Builder("http://localhost:8983/solr/taotao_core").build();
        //创建一个文档索引
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "test");
        document.addField("item_title", "测试商品1");
        document.addField("item_price", 123456789);
        //把文档对象写入索引库
        solrServer.add(document);
        //提交
        solrServer.commit();
    }
}
