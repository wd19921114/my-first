package cn.ppz111.creator.viewentity.user;

import lombok.Data;

/**
 * @author Yuki
 */
@Data
public class CommentVO {
    private String nickname;
    private Long userId;
    private String msg;
    private Long commentId;

}
