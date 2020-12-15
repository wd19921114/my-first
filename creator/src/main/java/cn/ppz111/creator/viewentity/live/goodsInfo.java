package cn.ppz111.creator.viewentity.live;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Yuki
 */
@Data
public class goodsInfo {

    private String coverImgUrl;

    private String name;

    private Integer priceType;

    private BigDecimal price;

    private BigDecimal price2;

    private String url;


}
