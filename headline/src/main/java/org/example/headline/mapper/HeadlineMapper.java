package org.example.headline.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.example.headline.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.headline.vo.PortalVo;

import java.util.Map;

/**
* @author 陈涛
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-05-07 14:56:45
* @Entity org.example.headline.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {


    @MapKey("hid")
    IPage<Map>selectMyPage(IPage page, @Param("portalVo") PortalVo portalVo);
}




