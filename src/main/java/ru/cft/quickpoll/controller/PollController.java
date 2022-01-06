package ru.cft.quickpoll.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.cft.quickpoll.exception.ResourceNotFoundException;
import ru.cft.quickpoll.model.Poll;
import ru.cft.quickpoll.repository.PollRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@Slf4j
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    @GetMapping("/polls")
    public ResponseEntity<?> getAllPolls(){
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity(allPolls, HttpStatus.OK);
    }

    @PostMapping("/polls")
    public ResponseEntity createPoll(@Valid @RequestBody Poll poll){
        log.info(String.format("poll with question [%s]", poll.getQuestion()));
        poll = pollRepository.save(poll);

        // Set the location header for newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/polls/{pollId}")
    public ResponseEntity getPoll(@PathVariable Long pollId) throws Exception{
        return new ResponseEntity(verifyPoll(pollId), HttpStatus.OK);
    }

    @PutMapping("/polls/{pollId}")
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll,  @PathVariable Long pollId){
        verifyPoll(pollId);
        pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/polls/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId){
        verifyPoll(pollId);
        pollRepository.deleteById(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected Poll verifyPoll(Long pollId){
        Optional<Poll> poll = pollRepository.findById(pollId);
        if (!poll.isPresent()) { throw new ResourceNotFoundException("Poll with id " + pollId + " not found");}
        return poll.get();
    }
}
