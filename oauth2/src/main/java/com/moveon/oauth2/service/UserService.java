package com.moveon.oauth2.service;

import com.moveon.oauth2.pojo.User;
import com.moveon.oauth2.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Moveon
 * @Date 2023/7/18 22:30
 * @Version 1.0
 **/
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPasswd(),
                    AuthorityUtils.createAuthorityList(user.getPasswd())
            );
        }
        throw new UsernameNotFoundException("User not found!");
    }
}
