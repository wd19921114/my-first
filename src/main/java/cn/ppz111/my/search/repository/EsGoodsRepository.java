package cn.ppz111.my.search.repository;

import cn.ppz111.my.search.doc.EsGoods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @author : yuki
 * @date : 2020/6/22
 */
public interface EsGoodsRepository extends ElasticsearchRepository<EsGoods,Long> {
}
