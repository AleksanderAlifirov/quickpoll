package ru.cft.quickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cft.quickpoll.model.Option;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {
}
