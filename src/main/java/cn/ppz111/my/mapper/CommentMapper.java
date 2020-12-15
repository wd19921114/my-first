package cn.ppz111.my.mapper;

import cn.ppz111.my.viewentity.user.CommentVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.ppz111.my.entity.MyComment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yuki
 */
@Repository
public interface CommentMapper extends BaseMapper<MyComment> {
    /**
     * 获取评论列表
     * @return list
     */
    @Select("select u.nickname,c.comment_id,c.msg,u.user_id from my_user u join my_comment c on u.`user_id`=c.`user_id` ")
    List<CommentVO> selectCommentList();
}
