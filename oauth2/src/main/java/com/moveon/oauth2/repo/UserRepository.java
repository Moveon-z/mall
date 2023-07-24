package com.moveon.oauth2.repo;

import com.moveon.oauth2.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserRepository
 * @Description TODO
 * @Author Moveon
 * @Date 2023/7/18 22:22
 * @Version 1.0
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String username);

}
