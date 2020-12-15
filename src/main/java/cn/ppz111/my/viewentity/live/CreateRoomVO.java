package cn.ppz111.my.viewentity.live;

import lombok.Data;

/**
 * @author Yuki
 */
@Data
public class CreateRoomVO {
    private String name;
    private String coverImg;
    private String startTime;
    private String endTime;
    private String anchorName;
    private String anchorWechat;
    private String shareImg;
    private Integer type;
    private Integer screenType;
    private Integer closeLike;
    private Integer closeGoods;
    private Integer closeComment;
}
