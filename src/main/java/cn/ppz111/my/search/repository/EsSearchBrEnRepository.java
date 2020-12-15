package cn.ppz111.my.search.repository;

import cn.ppz111.my.search.doc.EsSearchBrEn;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @author : yuki
 * @date : 2020/6/23
 */
public interface EsSearchBrEnRepository extends ElasticsearchRepository<EsSearchBrEn,Long> {
}
