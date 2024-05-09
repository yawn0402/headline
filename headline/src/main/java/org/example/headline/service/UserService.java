package org.example.headline.service;

import org.example.headline.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.headline.utils.Result;

/**
* @author 陈涛
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-05-07 14:56:45
*/
public interface UserService extends IService<User> {
    //登录业务
    Result login(User user);

    /**
     * 通过token获取user信息
     * @param token
     * @return
     */
    Result getUserInfo(String token);

    /**
     * 检查注册userName是否可用
     * @param userName
     * @return
     */
    Result checkUserName(String userName);

    Result regist(User user);
}
