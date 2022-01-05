package ru.cft.quickpoll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.cft.quickpoll.model.Poll;
import ru.cft.quickpoll.repository.PollRepository;

import java.net.URI;
import java.util.Optional;

@RestController
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    @GetMapping("/polls")
    public ResponseEntity getAllPolls(){
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity(allPolls, HttpStatus.OK);
    }

    @PostMapping("/polls")
    public ResponseEntity createPoll(@RequestBody Poll poll){
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
        Optional<Poll> poll = pollRepository.findById(pollId);
        return Optional.ofNullable(poll)
                .map(i -> i.get())
                .map(i -> new ResponseEntity(i, HttpStatus.OK))
                .orElseThrow(() -> new Exception("Poll not found"));
    }

    @PutMapping("/pollls/{pollId}")
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll,  @PathVariable Long pollId){
        Poll newPoll = pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/polls/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId){
        pollRepository.deleteById(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
