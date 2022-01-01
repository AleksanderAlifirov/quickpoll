package ru.cft.quickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cft.quickpoll.model.Vote;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {
}
