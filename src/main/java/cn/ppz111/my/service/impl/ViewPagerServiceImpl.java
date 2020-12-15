package cn.ppz111.my.service.impl;

import cn.ppz111.my.exception.CgErrorException;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ppz111.my.entity.ViewPager;
import cn.ppz111.my.enums.ErrorEnum;
import cn.ppz111.my.mapper.ViewPagerMapper;
import cn.ppz111.my.service.ViewPagerService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Yuki
 */
@Service
public class ViewPagerServiceImpl extends ServiceImpl<ViewPagerMapper, ViewPager> implements ViewPagerService {
    @Override
    public void addViewPage(String src, String name) {
        ViewPager pager=new ViewPager();
        pager.setSrc(src);
        pager.setName(name);
        baseMapper.insert(pager);
    }

    @Override
    public List<ViewPager> viewPageList() {
        return baseMapper.selectList(Wrappers.<ViewPager>lambdaQuery().last("limit 5"));
    }

    @Override
    public void updateViewPage(Integer index, String src, String name) {
        ViewPager one = getOne(Wrappers.<ViewPager>lambdaQuery().eq(ViewPager::getId, index));
        if(ObjectUtils.isEmpty(one)){
            throw new CgErrorException(ErrorEnum.USER_NOT_EXIST);
        }
        update(Wrappers.<ViewPager>lambdaUpdate().eq(ViewPager::getId,index).set(!StringUtils.isEmpty(name),ViewPager::getName,name).set(!StringUtils.isEmpty(src),ViewPager::getSrc,src));
    }
}
