package cn.ppz111.my.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.ppz111.my.entity.ViewPager;

import java.util.List;

/**
 * @author Yuki
 */
public interface ViewPagerService extends IService<ViewPager> {
    /**
     * 新增轮播图
     * @param src src
     * @param name name
     */
    void addViewPage(String src, String name);

    /**
     * 轮播图
     * @return list
     */
    List<ViewPager> viewPageList();

    /**
     * 跟新轮播图
     * @param index 下标
     * @param src src
     * @param name 名字
     */
    void updateViewPage(Integer index, String src, String name);
}
