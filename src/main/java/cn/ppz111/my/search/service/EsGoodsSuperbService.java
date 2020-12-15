package cn.ppz111.my.search.service;


import cn.ppz111.my.search.doc.EsSearchVO;
import cn.ppz111.my.search.doc.EsSuperbGoods;
import org.springframework.data.domain.Page;

/**
 * @author sh
 */
public interface EsGoodsSuperbService {

    /**
     * 搜索大宗商品
     * @param esSearchVO vo
     * @return page
     */
    Page<EsSuperbGoods> searchGroupGoods(EsSearchVO esSearchVO);
}
