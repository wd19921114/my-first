package cn.ppz111.my.search.doc;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * Description:
 *
 * @author : yuki
 * @date : 2020/6/23
 */
@Data
@Document(indexName = "es_enterprise")
public class EsSearchBrEn {

    @Id
    private Long enterpriseId;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String enterpriseName;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String englishName;

    private String logo;

    private Integer memberLevel;

    private String rangeName;

    @Field(type = FieldType.Nested)
    private List<EsBrand> brands;

    private List<CommonGoods> goods;

}
