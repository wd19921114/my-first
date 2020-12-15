package cn.ppz111.my.search.service;

import cn.ppz111.my.search.doc.EsGoods;
import cn.ppz111.my.search.doc.EsSearchBrEn;
import cn.ppz111.my.search.doc.EsSearchVO;
import org.springframework.data.domain.Page;

/**
 * Description:
 *
 * @author : yuki
 * @date : 2020/6/22
 */
public interface EsGoodsService {

    /**
     * 搜索
     * @param esSearchVO vo
     * @return page
     */
    Page<EsGoods> searchGoods(EsSearchVO esSearchVO);

    /**
     * 搜索企业品牌
     * @param keyword 关键字
     * @param userId 用户
     * @param pageNum 页数
     * @param pageSize 大小
     * @return page
     */
    Page<EsSearchBrEn> searchEnterprise(String keyword, Long userId, Integer pageNum, Integer pageSize);
}
