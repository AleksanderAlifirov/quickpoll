package ru.cft.quickpoll.client;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.cft.quickpoll.model.Poll;
import org.springframework.core.ParameterizedTypeReference;

import java.net.URI;
import java.util.List;

public class QuickPollClient {

    private static final String QUICK_POLL_URI_V1 = "http://localhost:8080/v1/polls";
    private RestTemplate restTemplate = new RestTemplate();

    public Poll getPollById(Long pollId){
        return restTemplate.getForObject(QUICK_POLL_URI_V1 + "/{pollId}", Poll.class, pollId);
    }

    public List<Poll> getAllPolls(){

        ParameterizedTypeReference<List<Poll>> responseType = new ParameterizedTypeReference<List<Poll>>() {};

        ResponseEntity<List<Poll>> responseEntity = restTemplate.exchange(QUICK_POLL_URI_V1, HttpMethod.GET, null, responseType);
        List<Poll> allPolls = responseEntity.getBody();

        return allPolls;
    }

    public URI createPoll(Poll poll){

        return restTemplate.postForLocation(QUICK_POLL_URI_V1, poll);
    }

    public void updatePoll(Poll poll){

        restTemplate.put(QUICK_POLL_URI_V1 + "/{pollId}", poll, poll.getId());
    }

    public void deletePoll(Long pollId){
        restTemplate.delete(QUICK_POLL_URI_V1 + "/{pollId}", pollId);
    }
}
