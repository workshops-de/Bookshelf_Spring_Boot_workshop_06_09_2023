package de.workshops.bookshelf.config.user;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends org.springframework.data.repository.Repository<User, String> {

    Optional<User> findUserByUsername(String username);

}
