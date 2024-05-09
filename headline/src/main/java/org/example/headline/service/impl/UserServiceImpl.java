package org.example.headline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.headline.pojo.User;
import org.example.headline.service.UserService;
import org.example.headline.mapper.UserMapper;
import org.example.headline.utils.JwtHelper;
import org.example.headline.utils.MD5Util;
import org.example.headline.utils.Result;
import org.example.headline.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author 陈涛
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-05-07 14:56:45
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{



    @Autowired
    private  UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;

    /*
    1. 根据用户对象查询
    2. 比对密码
    3. 账号为空，则返回501
    4. 密码错了，则返回503
    5. 成功返回一个token
     */
    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper();
        //查询条件
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        User user1 = userMapper.selectOne(lambdaQueryWrapper);
        if (user1==null){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        if(StringUtils.isNotEmpty(user.getUserPwd())&&
                MD5Util.encrypt(user.getUserPwd()).equals(user1.getUserPwd())){
            //密码正确，根据id生成token
            //将token封装到result返回
            String token = jwtHelper.createToken(Long.valueOf(user1.getUid()));
            System.out.println("CreatedToken:"+token);
            Map map=new HashMap<>();
            map.put("token",token);
            return Result.ok(map);
        }

        //wrong pwd
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
    }


    /**
     * 根据token获取用户数据
     * 1.token是否在有效期
     * 2. 获取userid
     * 3. 根据id获取其他信息
     * 4. 去掉密码，封装result返回
     * @param token
     * @return
     */
    @Override
    public Result getUserInfo(String token) {
//        System.out.println("RecivedToken:"+token);
        //true则是过期
        boolean expiration = jwtHelper.isExpiration(token);
        if (expiration){
            //token过期
            System.out.println("The token is expirated");
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        int userId=jwtHelper.getUserId(token).intValue();
        User user=userMapper.selectById(userId);
        user.setUserPwd("");
        Map data=new HashMap();
        data.put("loginuser",user);


        return Result.ok(data);
    }

    /**
     * 通过count查询
     * 如果count==0，则可用
     * @param userName
     * @return
     */
    @Override
    public Result checkUserName(String userName) {
//        System.out.println("userName:"+userName);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,userName);
        Long count = userMapper.selectCount(lambdaQueryWrapper);
        if (count==0) {
            return Result.ok(null);
        }

        return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }

    /**
     * 注册
     * 1. 先检查是否可用
     * 2. 插入数据库
     * @param user
     * @return
     */
    @Override
    public Result regist(User user) {
        System.out.println("user:"+user);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        Long count = userMapper.selectCount(lambdaQueryWrapper);
        if (count>0) {
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        userMapper.insert(user);
        return Result.ok(null);
    }
}




