package cn.ppz111.my.search.repository;

import cn.ppz111.my.search.doc.EsTailGoods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : xusuqing
 * @date : 2020/08/04/13:44
 */
public interface EsTailGoodsRepository extends ElasticsearchRepository<EsTailGoods,Long> {
}
