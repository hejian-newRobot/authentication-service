package com.service.auth;

import com.service.auth.dao.UserDao;
import com.service.auth.entity.Account;

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

        Account user = new Account();
        user.setUsername("12222");
        user.setFsRole("ROLE_ADMIN");
        user.setPwd(passwordEncoder.encode("123"));
        userDao.save(user);
    }

}
