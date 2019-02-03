package com.votingball.votingball.dao;

import com.votingball.votingball.entity.Poll;

import java.util.List;

public interface PollRepositoryUpdate {

    Poll saveAndFlush(Poll poll);
    List<Poll> saveAll(Iterable<Poll> polls);
}
