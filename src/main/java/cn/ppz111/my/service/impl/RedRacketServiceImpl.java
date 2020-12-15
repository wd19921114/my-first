package cn.ppz111.my.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import cn.ppz111.my.entity.RedPacketRecord;
import cn.ppz111.my.entity.RedRacket;
import cn.ppz111.my.mapper.RedRacketMapper;
import cn.ppz111.my.mapper.RedRacketRecordMapper;
import cn.ppz111.my.service.RedRacketService;
import cn.ppz111.my.utils.RedisLock;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Yuki
 */
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class RedRacketServiceImpl extends ServiceImpl<RedRacketMapper, RedRacket> implements RedRacketService {
    private final StringRedisTemplate redisTemplate;
    private final RedisLock redisLock;
    private final RedRacketRecordMapper redRacketRecordMapper;

    @Override
    public Integer startTwoSeckil(Long redPacketId, Long userId) {
        Integer money = 0;
        try {
            /**
             * 获取锁 保证红包数量和计算红包金额的原子性操作
             */
            boolean lock = redisLock.lock(redPacketId + "", System.currentTimeMillis() + 1L + "");
            if(lock){
                Long restPeople = redisTemplate.opsForValue().decrement(redPacketId + "-num", 1);
                assert restPeople != null;
                System.out.println("剩余人数:" + restPeople);
                if (restPeople.intValue() >= 0) {
                    /**
                     * 如果是最后一人
                     */
                    if (restPeople == 0) {
                        money = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get(redPacketId + "-money")));
                    } else {
                        Integer restMoney = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get(redPacketId + "-money")));
                        if (restMoney <= 0) {
                            return restMoney;
                        }
                        Random random = new Random();
                        //随机范围：[1,剩余人均金额的两倍]
                        money = random.nextInt((int) (restMoney / (restPeople+1) * 2 - 1)) + 1;
                    }
                    redisTemplate.opsForValue().decrement(redPacketId + "-money", money);
                    /**
                     * 异步入库
                     */
                    if (money > 0) {
                        System.out.println("入库");
                        RedPacketRecord record = new RedPacketRecord();
                        record.setMoney(money);
                        record.setRedPacketId(redPacketId);
                        record.setUid(userId.intValue());
                        record.setCreateTime(new Date());
                        record.setAmount(money);
                        redRacketRecordMapper.insert(record);
                    }
                }else {return 0;}
            }else{
                System.out.println("获取锁失败");
                redisTemplate.opsForValue().decrement(redPacketId + "-num", 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisLock.unlock(redPacketId + "", System.currentTimeMillis() + 1L + "");
        }
        return money;

    }


    @Override
    public void startTwo(long redPacketId) {
        int skillNum = 1000;
        //N个抢红包
        final CountDownLatch latch = new CountDownLatch(skillNum);
        /**
         * 初始化红包数据，抢红包拦截
         */
        redisTemplate.opsForValue().set(redPacketId + "-num", "50");
        /**
         * 初始化红包金额，单位为分
         */
        redisTemplate.opsForValue().set(redPacketId + "-money", "1000");
        /**
         * 模拟100个用户抢10个红包
         */
        String s;
        for (int i = 1; i <= skillNum; i++) {
            int userId = i;
            Runnable task = () -> {
                /**
                 * 抢红包 判断剩余金额
                 */
                String money = redisTemplate.opsForValue().get(redPacketId + "-money");
                System.out.println(money);
                String s1 = redisTemplate.opsForValue().get(redPacketId + "-num" );
                if (Integer.parseInt(money) > 0&&Integer.parseInt(s1)>0) {
                    /**
                     * 虽然能抢到 但是不一定能拆到
                     * 类似于微信的 点击红包显示抢的按钮
                     */
                    startTwoSeckil(redPacketId, (long) userId);
                }
                latch.countDown();
            };
            ThreadUtil.execute(task);
            System.out.println(redisTemplate.opsForValue().get(redPacketId + "-num" ));
        }
        try {
            latch.await();
            s = redisTemplate.opsForValue().get(redPacketId + "-money");
            System.out.println("剩余金额：{" + s + "}");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getRedPacket(long redPacketId) {
        redisTemplate.opsForValue().set("allRedPacket", "100");
        redisTemplate.opsForValue().set("num", "10");
        for (int a = 0; a < 11; a++) {
            int finalA = a;
            Runnable runnable = () -> {
                if (redisTemplate.opsForValue().setIfAbsent("getKey", "thisIsKey")) {
                    Integer num = redisTemplate.opsForValue().decrement("num", 1).intValue();
                    String allRedPacket = redisTemplate.opsForValue().get("allRedPacket");
                    assert allRedPacket != null;
                    Integer s = Integer.parseInt(allRedPacket);
                    if (s > 0 && num > 0) {
                        if (!StringUtils.isEmpty(allRedPacket)) {
                            Random random = new Random();
                            int i = s / num;
                            if (i <= 0) {
                                i = 1;
                            } else {
                                i = random.nextInt(i);
                                if (i == 0) {
                                    i = 1;
                                }
                            }
                            redisTemplate.opsForValue().decrement("allRedPacket", i);
                            RedPacketRecord record = new RedPacketRecord();
                            record.setMoney(i);
                            record.setRedPacketId(redPacketId);
                            record.setUid(finalA);
                            record.setCreateTime(new Date());
                            record.setAmount(i);
                            redRacketRecordMapper.insert(record);
                        }
                    }
                    redisTemplate.delete("getKey");
                }
            };
            ThreadUtil.execute(runnable);
        }

    }
}
