package com.Webtube.site.Repository;

import com.Webtube.site.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String admin);

    User findOneByUsernameAndPassword(String username, String password);
}
