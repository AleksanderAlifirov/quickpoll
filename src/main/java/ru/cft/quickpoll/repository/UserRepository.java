package ru.cft.quickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cft.quickpoll.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUserName(String name);
}
