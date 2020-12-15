package cn.ppz111.my.search.doc;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Description:
 *
 * @author : yuki
 * @date : 2020/6/23
 */
@Data
public class CommonGoods {

    private Long spuId;

    private String primaryImg;

    private String goodsName;

    private Boolean ifNew;

    private BigDecimal price;
}
