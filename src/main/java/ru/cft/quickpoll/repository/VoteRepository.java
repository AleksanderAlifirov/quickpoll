package ru.cft.quickpoll.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cft.quickpoll.model.Vote;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {
    @Query(value = "SELECT v.* FROM qp_votes v " +
                   "JOIN qp_options o ON o.ID = v.option_id " +
                   "WHERE o.poll_id = ?1", nativeQuery = true)
    public Iterable<Vote> findByPoll(Long pollId);
}
