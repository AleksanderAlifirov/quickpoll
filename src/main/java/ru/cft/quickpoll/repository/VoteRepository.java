package ru.cft.quickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cft.quickpoll.model.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {
}
