package cn.ppz111.my.search.repository;


import cn.ppz111.my.search.doc.EsSuperbGoods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author www42
 */
public interface EsSuperbGoodsRepository extends ElasticsearchRepository<EsSuperbGoods,Long> {
}
