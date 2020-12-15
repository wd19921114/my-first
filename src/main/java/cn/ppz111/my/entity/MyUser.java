package cn.ppz111.my.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import cn.ppz111.my.enums.UserStatusEnum;
import lombok.Data;

/**
 * @author Yuki
 */
@Data
public class MyUser {
    @TableId
    private Long userId;
    private String nickname;
    private String avatarUrl;
    private Integer age;
    private String info;
    private String passWd;
    private String phone;
    private UserStatusEnum status;
}
