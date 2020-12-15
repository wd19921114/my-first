package cn.ppz111.my.search.service.impl;


import cn.ppz111.my.search.doc.EsSearchVO;
import cn.ppz111.my.search.repository.EsSuperbGoodsRepository;
import cn.ppz111.my.search.doc.EsSuperbGoods;
import cn.ppz111.my.search.service.EsGoodsSuperbService;
import lombok.AllArgsConstructor;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author sh
 */
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class EsGoodsSuperbServiceImpl implements EsGoodsSuperbService {

    private final EsSuperbGoodsRepository esSuperbGoodsRepository;

   @Override
    public Page<EsSuperbGoods> searchGroupGoods(EsSearchVO esSearchVO) {
        Pageable pageable = PageRequest.of(esSearchVO.getPageNum(), esSearchVO.getPageSize());
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分页
        nativeSearchQueryBuilder.withPageable(pageable);
            nativeSearchQueryBuilder.withFields("groupSpuId","goodsName","englishName","primaryImg","brandId","brandName","labels","enterpriseId", "enterpriseName", "enterpriseEnglishName","categoryId","categoryName","price","memberLevel");
        //过滤
        if (!CollectionUtils.isEmpty(esSearchVO.getBrandIds())
                || !CollectionUtils.isEmpty(esSearchVO.getCategoryIds())
                ||!CollectionUtils.isEmpty(esSearchVO.getLabelIds())||
                !StringUtils.isEmpty(esSearchVO.getMinPrice()) ) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (!CollectionUtils.isEmpty(esSearchVO.getBrandIds())) {
                boolQueryBuilder.must(QueryBuilders.termsQuery("brandId", esSearchVO.getBrandIds()));
            }
            if (!CollectionUtils.isEmpty(esSearchVO.getCategoryIds())) {
                boolQueryBuilder.must(QueryBuilders.termsQuery("categoryId", esSearchVO.getCategoryIds()));
            }
            if (!CollectionUtils.isEmpty(esSearchVO.getLabelIds())) {
                QueryBuilder itemsQuery = QueryBuilders.nestedQuery("labels",
                        QueryBuilders.boolQuery().must(QueryBuilders.termsQuery("labels.labelId", esSearchVO.getLabelIds())), ScoreMode.Total);
                boolQueryBuilder.must(itemsQuery);
            }
            if (!StringUtils.isEmpty(esSearchVO.getMinPrice())&&!StringUtils.isEmpty(esSearchVO.getMaxPrice())){
                    boolQueryBuilder.must(QueryBuilders.rangeQuery("price").from(esSearchVO.getMinPrice()).to(esSearchVO.getMaxPrice()));
            }
            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
        }
        //搜索
        String keyword = esSearchVO.getKeyword();
        if (StringUtils.isEmpty(keyword)) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(keyword,"goodsName","brandName","englishName"));
            boolQueryBuilder.should(QueryBuilders.matchPhraseQuery("goodsName", keyword));
            boolQueryBuilder.should(QueryBuilders.matchPhraseQuery("brandName", keyword));
            boolQueryBuilder.should(QueryBuilders.matchPhraseQuery("englishName", keyword));
            nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        }
        Integer sort = esSearchVO.getSort();
        if (StringUtils.isEmpty(sort)){
            sort = 0;
        }
        //排序
        if(sort==1){
            //按新品从新到旧
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("groupSpuId").order(SortOrder.DESC));
        }else if(sort==2){
            //按价格从低到高
                nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        }else if(sort==3) {
            //按价格从高到低
                nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        }else {
            //按相关度
            nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        }
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        return esSuperbGoodsRepository.search(searchQuery);
    }

}
