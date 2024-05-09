package org.example.headline.service;

import org.example.headline.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.headline.utils.Result;
import org.example.headline.vo.PortalVo;

/**
* @author 陈涛
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-05-07 14:56:45
*/
public interface HeadlineService extends IService<Headline> {

    /**
     * 1.自定义查询语句
     * 2.分页数据，拼接道result
     *
     * @param portalVo
     * @return
     */
    Result findNewsPage(PortalVo portalVo);
}
