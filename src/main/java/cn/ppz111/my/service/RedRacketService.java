package cn.ppz111.my.service;

import cn.ppz111.my.entity.RedRacket;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Yuki
 */
public interface RedRacketService extends IService<RedRacket> {
    /**
     * 拆红包
     * @param redRacketId id
     * @param userId id
     */
    Integer  startTwoSeckil(Long redRacketId,Long userId);

    void startTwo(long redPacketId);

    void getRedPacket(long redPacketId);
}
