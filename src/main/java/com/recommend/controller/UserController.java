package com.recommend.controller;

import com.recommend.bean.User;
import com.recommend.service.UserService;
import com.recommend.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public JsonData login(@RequestBody User user) {

        if (!userService.login(user)) {
            return JsonData.buildError("登录失败");
        }

        return JsonData.buildSuccess("登录成功");
    }

    @PostMapping("/register")
    public JsonData register(@RequestBody User user) {

        System.out.println(user);
        if (userService.register(user)) {
            return JsonData.buildSuccess("注册成功");
        }

        return JsonData.buildError("注册成功");
    }

    @RequestMapping(value = "/del/{userName}", method = RequestMethod.POST)
    public JsonData delUser(@PathVariable("userName") String userName) {

        if (userService.delUser(userName)) {
            return JsonData.buildSuccess("删除成功");
        } else {
            return JsonData.buildError("删除失败");
        }
    }

    @RequestMapping(value = "/getInfo/{userName}", method = RequestMethod.POST)
    public JsonData getUser(@PathVariable("userName") String userName) {

        return JsonData.buildSuccess(userService.getUser(userName));
    }

}
