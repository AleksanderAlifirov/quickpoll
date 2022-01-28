package ru.cft.quickpoll.v3.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.cft.quickpoll.error.ErrorDetail;
import ru.cft.quickpoll.exception.ResourceNotFoundException;
import ru.cft.quickpoll.model.Poll;
import ru.cft.quickpoll.repository.PollRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController("pollControllerV3")
@RequestMapping("/v3")
@Slf4j
@Api(value = "polls", description = "Poll API")
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    @ApiOperation(value = "Retrieves all polls", response = Poll.class, responseContainer = "List")
    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<Page<Poll>> getAllPolls(Pageable pageable){
        Page<Poll> allPolls = pollRepository.findAll(pageable);
        return new ResponseEntity(allPolls, HttpStatus.OK);
    }

    @ApiOperation(value = "Creates a new Poll", notes = "The newly created poll Id will be sent in the response header", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Poll Created Successfully", response = Void.class),
                           @ApiResponse(code = 500,  message = "Error Creating Poll", response = ErrorDetail.class)})
    @PostMapping("/polls")
    public ResponseEntity<Void> createPoll(@Valid @RequestBody Poll poll){
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

    @ApiOperation(value = "Retrieves a Poll associated with the pollId", response = Poll.class)
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
