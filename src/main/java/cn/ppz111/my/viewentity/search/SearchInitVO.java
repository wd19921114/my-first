package cn.ppz111.my.viewentity.search;

import lombok.Data;

import java.util.List;

/**
 * Description:
 *
 * @author : yuki
 * @date : 2020/6/20
 */
@Data
public class SearchInitVO {

    private List<IndexViewVO> viewList;

    private List<SuggestVO> rl;
}
