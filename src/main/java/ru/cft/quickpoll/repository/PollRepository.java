package ru.cft.quickpoll.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.cft.quickpoll.model.Poll;

@Repository
public interface PollRepository extends PagingAndSortingRepository<Poll, Long> {

}
