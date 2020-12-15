package cn.ppz111.my.controller;

import cn.ppz111.my.search.doc.*;
import cn.ppz111.my.search.service.EsGoodsService;
import cn.ppz111.my.search.service.EsGoodsSuperbService;
import cn.ppz111.my.search.service.EsTailGoodsService;
import cn.ppz111.my.viewentity.ResultVO;
import cn.ppz111.my.utils.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 *
 * @author : yuki
 * @date : 2020/6/20
 */
@RestController
@RequestMapping("search")
@AllArgsConstructor
public class SearchController {

    private final EsGoodsService searchService;

    private final EsTailGoodsService esTailGoodsService;

    private final EsGoodsSuperbService esGoodsSuperbService;


    /**
     * 商品搜索
     */
    @PostMapping("searchGoods")
    public ResultVO<Object> searchGoods(@RequestBody EsSearchVO esSearchVO) {
        Page<EsGoods> esGoods = searchService.searchGoods(esSearchVO);
        return ResultUtil.success(CommonPage.restPage(esGoods));
    }

    /**
     * 尾货商品搜索
     */
    @PostMapping("searchTailGoods")
    public ResultVO<Object> searchTailGoods(@RequestBody EsSearchVO esSearchVO) {
        Page<EsTailGoods> esTailGoods = esTailGoodsService.searchTailGoods(esSearchVO);
        return ResultUtil.success(CommonPage.restPage(esTailGoods));
    }

    /**
     * 搜索企业
     */
    @PostMapping("searchBrandAndEnterprise")
    public ResultVO<Object> searchEnterprise(@RequestParam("keyword") String keyword,
                                                               @RequestParam("userId") Long userId,
                                                               @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<EsSearchBrEn> vo = searchService.searchEnterprise(keyword, userId, pageNum, pageSize);
        return ResultUtil.success(CommonPage.restPage(vo));
    }

    /**
     * 大宗商品搜索
     */
    @PostMapping("searchGroupGoods")
    public ResultVO<Object> searchGroupGoods(@RequestBody EsSearchVO esSearchVO) {
        Page<EsSuperbGoods> esGroupGoods = esGoodsSuperbService.searchGroupGoods(esSearchVO);
        return ResultUtil.success(CommonPage.restPage(esGroupGoods));
    }
}
