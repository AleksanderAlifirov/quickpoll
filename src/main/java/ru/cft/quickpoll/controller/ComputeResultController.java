package ru.cft.quickpoll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.quickpoll.model.OptionCount;
import ru.cft.quickpoll.model.Vote;
import ru.cft.quickpoll.model.VoteResult;
import ru.cft.quickpoll.repository.VoteRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
public class ComputeResultController {

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping("/computeresult")
    public ResponseEntity<?> computeResult(@RequestParam Long pollId){
        VoteResult voteResult;
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);

        voteResult = computeVoteResult(allVotes);
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }

    private VoteResult computeVoteResult(Iterable<Vote> allVotes){
        VoteResult voteResult = new VoteResult();
        int totalVotes = 0;
        Map<Long, OptionCount> tmpMap = new HashMap<>();
        for (Vote v : allVotes){
            totalVotes++;
            OptionCount optionCount = tmpMap.get(v.getOption().getId());
            if (optionCount == null) {
                        optionCount = new OptionCount();
                        optionCount.setOptionId(v.getOption().getId());
                        tmpMap.put(v.getOption().getId(), optionCount);
                    }
            optionCount.setCount(optionCount.getCount() + 1);

        }

        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tmpMap.values());

        return voteResult;

    }

}
