package ru.cft.quickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cft.quickpoll.model.Option;

public interface OptionRepository extends CrudRepository<Option, Long> {
}
