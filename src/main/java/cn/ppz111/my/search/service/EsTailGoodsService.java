package cn.ppz111.my.search.service;

import cn.ppz111.my.search.doc.EsSearchVO;
import cn.ppz111.my.search.doc.EsTailGoods;
import org.springframework.data.domain.Page;


/**
 * Created with IntelliJ IDEA.
 *
 * @author : xusuqing
 * @date : 2020/08/04/13:42
 */
public interface EsTailGoodsService {
    /**
     * 搜索尾货商品
     * @param esSearchVO vo
     * @return page
     */
    Page<EsTailGoods> searchTailGoods(EsSearchVO esSearchVO);
}
