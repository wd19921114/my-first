package cn.ppz111.creator.viewentity.live;

import lombok.Data;

import java.util.List;

/**
 * @author Yuki
 */
@Data
public class AddGoodsToRoomVO {
        private List<Integer> ids;
        private Integer roomId;
}
