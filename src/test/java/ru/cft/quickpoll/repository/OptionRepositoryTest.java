package ru.cft.quickpoll.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.cft.quickpoll.model.Option;

@SpringBootTest
public class OptionRepositoryTest {

    @Autowired
    OptionRepository optionRepository;

    @Test
    public void saveOptionRepository(){
        Option option = Option.builder().pollId(1L).value("When you in love someone").build();
        optionRepository.save(option);
    }
}
