package com.dotseven.poc.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE username=:username AND password=:password", nativeQuery = true)
    List<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
