package org.example.headline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.headline.pojo.Type;
import org.example.headline.service.TypeService;
import org.example.headline.mapper.TypeMapper;
import org.springframework.stereotype.Service;

/**
* @author 陈涛
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-05-07 14:56:45
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

}




