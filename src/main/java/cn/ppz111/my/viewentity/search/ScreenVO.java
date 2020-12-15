package cn.ppz111.my.viewentity.search;

import cn.ppz111.my.search.doc.EsBrand;
import cn.ppz111.my.search.doc.EsCategory;
import cn.ppz111.my.search.doc.EsLabel;
import lombok.Data;

import java.util.List;

/**
 * Description:
 *
 * @author : yuki
 * @date : 2020/6/22
 */
@Data
public class ScreenVO {

    private List<EsLabel> labels;

    private List<EsCategory> categories;

    private List<EsBrand> brands;

    private String cityName;

}
