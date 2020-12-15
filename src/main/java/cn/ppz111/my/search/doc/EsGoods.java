package cn.ppz111.my.search.doc;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Description:
 *
 * @author : yuki
 * @date : 2020/6/22
 */
@Data
@Document(indexName = "es_goods")
public class EsGoods implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    private Long spuId;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String goodsName;

    private String synopsis;

    private String primaryImg;

    private Long enterpriseId;

    private String enterpriseName;

    private String enterpriseEnglishName;

    private Integer memberLevel;

    @Field(type = FieldType.Nested)
    private List<EsCity> cities;

    private Long brandId;

    @Field(type = FieldType.Keyword)
    private String brandName;

    @Field(type = FieldType.Keyword)
    private String englishName;

    @Field(type = FieldType.Nested)
    private List<EsLabel> labels;

    private Long categoryId;

    @Field(type = FieldType.Keyword)
    private String categoryName;

    private BigDecimal retailPrice;

    private BigDecimal wholesalePrice;
}
