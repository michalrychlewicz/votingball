package com.votingball.votingball.rest;

import com.votingball.votingball.dao.PollsDao;
import com.votingball.votingball.entity.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PollRestController {

    private static final String POLL_CANNOT_BE_NULL_MESSAGE = "Poll must be provided for save operation.";
    private String POLL_WITH_GIVEN_ID_NOT_EXIST_MESSAGE = "Poll with given id does not exist: ";

    private PollsDao pollsDao;
    private PollPositionsHelper pollPositionsHelper;

    @Autowired
    public PollRestController(PollsDao pollsDao) {
        this.pollsDao = pollsDao;
        pollPositionsHelper = new PollPositionsHelper();
    }

    @GetMapping("/polls")
    public List<Poll> findAll() {
        return pollsDao.findAll();
    }


    @GetMapping("/polls/{pollId}")
    public Poll getPoll(@PathVariable int pollId) {
        Poll poll = pollsDao.findById(pollId);
        if(poll == null)
        {
            throw new RuntimeException(POLL_WITH_GIVEN_ID_NOT_EXIST_MESSAGE +pollId);
        }
        return poll;
    }


    @PostMapping("/polls")
    public Poll addPoll(@RequestBody Poll poll) {
        if(poll == null)
        {
            throw new RuntimeException(POLL_CANNOT_BE_NULL_MESSAGE);
        }
        poll.setId(0);
        pollPositionsHelper.setPositionsReferenceToPoll(poll,poll.getPositions());
        pollsDao.save(poll);
        return poll;
    }

    @PutMapping("/polls")
    public Poll updatePoll(@RequestBody Poll poll) {
        if(poll == null)
        {
            throw new RuntimeException(POLL_CANNOT_BE_NULL_MESSAGE);
        }
        pollPositionsHelper.setPositionsReferenceToPoll(poll,poll.getPositions());
        pollsDao.save(poll);
        return poll;
    }

    @DeleteMapping("/polls/{pollId}")
    public String deletePoll(@PathVariable int pollId) {
        Poll tempPoll = pollsDao.findById(pollId);
        if (tempPoll == null) {
            throw new RuntimeException("Poll id not found: " + pollId);
        }
        pollsDao.deleteById(pollId);
        return "Deleted poll id: " + pollId;
    }
}
