package cn.ppz111.my.service.impl;

import cn.hutool.aop.proxy.JdkProxyFactory;
import cn.ppz111.my.viewentity.user.CommentVO;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ppz111.my.entity.MyComment;
import cn.ppz111.my.enums.ErrorEnum;
import cn.ppz111.my.exception.CgErrorException;
import cn.ppz111.my.mapper.CommentMapper;
import cn.ppz111.my.service.CommentService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yuki
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, MyComment> implements CommentService {
    @Override
    public void setComment(Long userId, String msg) {
        MyComment one = getOne(Wrappers.<MyComment>lambdaQuery().eq(MyComment::getUserId, userId));
        if(!ObjectUtils.isEmpty(one)){
            throw new CgErrorException(ErrorEnum.OSS_ERROR);
        }
        MyComment comment=new MyComment();
        comment.setUserId(userId);
        comment.setMsg(msg);
        baseMapper.insert(comment);
    }

    @Override
    public List<CommentVO> commentList() {
        return baseMapper.selectCommentList();
    }

}
