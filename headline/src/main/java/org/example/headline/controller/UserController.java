package org.example.headline.controller;


import org.example.headline.pojo.User;
import org.example.headline.service.HeadlineService;
import org.example.headline.service.UserService;
import org.example.headline.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HeadlineService headlineService;

    @PostMapping("login")
    public Result login(@RequestBody User user) {//接收json数据，要加注解,默认为param
        Result result = userService.login(user);
        return result;
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token) {
        Result result = userService.getUserInfo(token);
        return result;
    }

    @PostMapping("checkUserName")
    public Result checkUserName(String userName) {
        Result result = userService.checkUserName(userName);
        return result;
    }

    @PostMapping("regist")
    public Result regist(@RequestBody User user) {
        Result result = userService.regist(user);
        return result;

    }


}