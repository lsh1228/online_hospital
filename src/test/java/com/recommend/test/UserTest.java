package com.recommend.test;

import com.recommend.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.recommend.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
@SpringBootTest
public class UserTest {

    @Autowired
    UserService userService;

    @Test
    public void testRegister() {

        User user = new User();
        user.setName("aa");
        user.setPassword("123");
        boolean ok = userService.register(user);
        assert(!ok);

        User user2 = userService.getUser("aa");
        assert(user2 != null);
    }

    public void testLogin() {

        User user = new User();
        user.setName("aa");
        user.setPassword("123");
        boolean ok = userService.login(user);
        assert(ok);
    }

}
