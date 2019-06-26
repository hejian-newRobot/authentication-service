package com.service.auth.service;

import com.service.auth.dao.UserDao;
import com.service.auth.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserDao userRepository;

    @Autowired
    public UserDetailService(UserDao userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User account = userRepository.findByUserName(username);
        if (account != null) {
            return new org.springframework.security.core.userdetails.User(account.getUserName(),
                    "{noop}" + account.getPassWord(), AuthorityUtils.createAuthorityList(account.getRoles().split(",")));
        } else {
            throw new UsernameNotFoundException("用户[" + username + "]不存在");
        }
    }
}
