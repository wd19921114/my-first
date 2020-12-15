package cn.ppz111.my.service;

import cn.ppz111.my.viewentity.user.CommentVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.ppz111.my.entity.MyComment;

import java.util.List;

/**
 * @author Yuki
 */
public interface CommentService extends IService<MyComment> {
    /**
     * s
     * @param userId s
     * @param msg s
     */
    void setComment(Long userId, String msg);

    /**
     * list
     * @return list
     */
    List<CommentVO> commentList();
}
