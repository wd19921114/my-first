package cn.ppz111.my.viewentity.user;

import lombok.Data;

/**
 * Description:
 * 首页顶部栏信息
 * @author : Yuki
 * @date : 2020/6/22
 */
@Data
public class IndexTopBarVO {
    private String avatarUrl;
    private String nickname;
    private Integer msgNum;
    private Integer leftVipDays;
    private String memberLevel;
}
