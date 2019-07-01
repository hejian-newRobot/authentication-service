package com.service.auth;

import com.service.auth.dao.UserDao;
import com.service.auth.entity.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceAuthApplicationTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void contextLoads() {

        User user = new User();
        user.setJobId("12222");
        user.setUserName("何健");
        user.setPsd(passwordEncoder.encode("123"));
        userDao.save(user);
    }

}
