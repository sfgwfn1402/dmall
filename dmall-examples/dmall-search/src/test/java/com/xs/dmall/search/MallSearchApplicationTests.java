package com.xs.dmall.search;

import com.xs.dmall.search.dao.EsProductDao;
import com.xs.dmall.search.domain.EsProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallSearchApplicationTests {
    @Autowired
    private EsProductDao productDao;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Test
    public void contextLoads() {
    }
    @Test
    public void testGetAllEsProductList(){
        List<EsProduct> esProductList = productDao.getAllEsProductList(null);
        System.out.print(esProductList);
    }

    /**
     * 创建索引
     * 配置映射
     */
    @Test
    public  void createIndex(){
        elasticsearchTemplate.createIndex(EsProduct.class);
        elasticsearchTemplate.putMapping(EsProduct.class);

    }

    @Test
    public void testEsProductMapping(){
//        IndexOperations indexOperations = elasticsearchTemplate.indexOps(EsProduct.class);
//        indexOperations.putMapping(indexOperations.createMapping(EsProduct.class));
//        Map mapping = indexOperations.getMapping();
//        System.out.println(mapping);
    }

}
