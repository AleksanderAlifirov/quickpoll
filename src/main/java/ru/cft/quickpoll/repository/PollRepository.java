package ru.cft.quickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cft.quickpoll.model.Poll;

public interface PollRepository extends CrudRepository<Poll, Long> {
}
