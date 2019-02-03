package com.votingball.votingball.rest;

import com.votingball.votingball.dao.PollsRepository;
import com.votingball.votingball.entity.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PollRestController {

    private static final String POLL_CANNOT_BE_NULL_MESSAGE = "Poll must be provided for save operation.";
    private String POLL_WITH_GIVEN_ID_NOT_EXIST_MESSAGE = "Poll with given id does not exist: ";

    private PollsRepository pollsRepository;
    private PollPositionsHelper pollPositionsHelper;

    @Autowired
    public PollRestController(PollsRepository pollsDao) {
        this.pollsRepository = pollsDao;
        pollPositionsHelper = new PollPositionsHelper();
    }

    @GetMapping("/polls")
    public List<Poll> findAll() {
        return pollsRepository.findAll();
    }


    @GetMapping("/polls/{pollId}")
    public Poll getPoll(@PathVariable int pollId) {
        Optional<Poll> poll = pollsRepository.findById(pollId);
        if(!poll.isPresent())
        {
            throw new RuntimeException(POLL_WITH_GIVEN_ID_NOT_EXIST_MESSAGE +pollId);
        }
        return poll.get();
    }


    @PostMapping("/polls")
    public Poll addPoll(@RequestBody Poll poll) {
        if(poll == null)
        {
            throw new RuntimeException(POLL_CANNOT_BE_NULL_MESSAGE);
        }
        poll.setId(0);
        pollPositionsHelper.setPositionsReferenceToPoll(poll,poll.getPositions());
        pollsRepository.save(poll);
        return poll;
    }

    @PutMapping("/polls")
    public Poll updatePoll(@RequestBody Poll poll) {
        if(poll == null)
        {
            throw new RuntimeException(POLL_CANNOT_BE_NULL_MESSAGE);
        }
        pollPositionsHelper.setPositionsReferenceToPoll(poll,poll.getPositions());
        pollsRepository.save(poll);
        return poll;
    }

    @DeleteMapping("/polls/{pollId}")
    public String deletePoll(@PathVariable int pollId) {
        Optional<Poll> tempPoll = pollsRepository.findById(pollId);
        if (!tempPoll.isPresent()) {
            throw new RuntimeException("Poll id not found: " + pollId);
        }
        pollsRepository.deleteById(pollId);
        return "Deleted poll id: " + pollId;
    }
}
