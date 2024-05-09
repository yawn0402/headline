package org.example.headline;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.example.headline.mapper.HeadlineMapper;
import org.example.headline.mapper.UserMapper;
import org.example.headline.pojo.Headline;
import org.example.headline.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MulTableTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HeadlineMapper headlineMapper;

    @Test
    public void seletTest(){
        MPJLambdaWrapper<User> mpjLambdaWrapper=new MPJLambdaWrapper<>();

        mpjLambdaWrapper.selectAll(User.class)//先是左边的主表
                .select(Headline::getTitle)
                .leftJoin(Headline.class,Headline::getHid,User::getUid);

        List<User> users = userMapper.selectList(mpjLambdaWrapper);
        System.out.println(users);


        return;
    }
}
