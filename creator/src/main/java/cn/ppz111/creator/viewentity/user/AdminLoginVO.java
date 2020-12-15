package cn.ppz111.creator.viewentity.user;

import lombok.Data;

/**
 * @author Yuki
 */
@Data
public class AdminLoginVO {
    private Long userId;
    private String nickname;
    private String avatarUrl;
    private Integer age;
    private String info;
    private String phone;
    private String token;
    private Integer status;
}
