package com.votingball.votingball.dao;

import com.votingball.votingball.entity.Poll;

import java.util.List;

public interface PollsDao {

    List<Poll> findAll();
    Poll findById(int id);
    void save(Poll poll);
    void update(Poll poll);
    void deleteById(int id);
}
