package com.votingball.votingball.dao;

import com.votingball.votingball.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollsRepository extends JpaRepository<Poll,Integer> {
}
