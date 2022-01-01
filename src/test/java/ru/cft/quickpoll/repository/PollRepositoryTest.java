package ru.cft.quickpoll.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.cft.quickpoll.model.Poll;

@SpringBootTest
public class PollRepositoryTest {

    @Autowired
    PollRepository pollRepository;

    @Test
    public void pollSaveTest(){
        Poll poll = Poll.builder().question("What is love").build();
        pollRepository.save(poll);
    }
}
