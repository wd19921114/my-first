package cn.ppz111.my.search.doc;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Description:
 *
 * @author : yuki
 * @date : 2020/6/22
 */
@Data
public class EsSearchVO {

    private Long userId;

    private Long shopId;

    private String keyword;

    private List<Long> brandIds;

    private List<Long> categoryIds;

    private List<Long> labelIds;

    private Integer pageNum;

    private Integer pageSize;

    private Integer sort;

    private Integer memberLevel;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

}
