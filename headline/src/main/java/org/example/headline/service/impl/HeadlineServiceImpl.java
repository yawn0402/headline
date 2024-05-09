package org.example.headline.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.headline.pojo.Headline;
import org.example.headline.service.HeadlineService;
import org.example.headline.mapper.HeadlineMapper;
import org.example.headline.utils.Result;
import org.example.headline.vo.PortalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 陈涛
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-05-07 14:56:45
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{
    @Autowired
    private HeadlineMapper headlineMapper;

    @Override
    public Result findNewsPage(PortalVo portalVo) {

        IPage<Map>page=new Page<>();
        headlineMapper.selectMyPage(page,portalVo);
        List<Map>records=page.getRecords();
        Map pageinfo=new HashMap();
        pageinfo.put("pagedata",records);
        Map data=new HashMap<>();
        data.put("pageinfo",pageinfo);

        return Result.ok(data);
    }
}




