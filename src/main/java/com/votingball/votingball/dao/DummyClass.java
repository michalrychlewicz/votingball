package com.votingball.votingball.dao;

import com.votingball.votingball.entity.Poll;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class DummyClass {

    public List<Poll> findAll()
    {
      return Arrays.asList(new Poll("dummy",LocalDateTime.now(),LocalDateTime.now()));
    }
}
