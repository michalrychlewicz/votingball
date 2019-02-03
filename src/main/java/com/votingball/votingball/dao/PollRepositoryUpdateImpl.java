package com.votingball.votingball.dao;

import com.votingball.votingball.entity.Poll;

import java.util.List;

public class PollRepositoryUpdateImpl implements PollRepositoryUpdate {

    @Override
    public Poll saveAndFlush(Poll poll) {
        System.out.println("Saving poll");
        return null;
    }

    @Override
    public List<Poll> saveAll(Iterable<Poll> polls) {
        System.out.println("Saving poll");
        return null;
    }
}
