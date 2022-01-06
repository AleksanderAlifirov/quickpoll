package ru.cft.quickpoll.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class PollTest {

    private String emptyQuestionPoll = "{       \"options\":\n" +
            "    [\n" +
            "        {\"value\": \"Good\"},\n" +
            "        {\"value\": \"Worse\"},\n" +
            "        {\"value\": \"Nothing\"}\n" +
            "    ]\n" +
            "}";

    @Test
    public void serializedPoll() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Poll poll = objectMapper.readValue(emptyQuestionPoll, Poll.class);
        System.out.println(poll.getOptions().toString());
    }
}
