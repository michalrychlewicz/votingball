package com.votingball.votingball.dao;

import com.mysql.cj.exceptions.AssertionFailedException;
import com.votingball.votingball.entity.Poll;
import com.votingball.votingball.entity.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

/**
 * @author Michal Rychlewicz
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PollsRepositoryIntegrationTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PollsRepository employeeRepository;


    @Test
    public void whenFindById_thenReturnPoll() {
        // given
        Poll pollBeingInserted = preparePollForInsertion();
        Poll persistedPoll = entityManager.persist(pollBeingInserted);
        entityManager.flush();
        int pollId = persistedPoll.getId();

        // when
        Optional<Poll> retrievedPoll = employeeRepository.findById(pollId);
        if(!retrievedPoll.isPresent())
        {
            throw new AssertionFailedException("Poll was just persisted. It has to be present in database.");
        }

        // then
        Poll actualPoll = retrievedPoll.get();
        assertThat(actualPoll,is(equalTo(pollBeingInserted)));
    }

    private Poll preparePollForInsertion() {
        String title = "Poll title";
        LocalDateTime createdAt = LocalDateTime.of(2019,02,04,10,30);
        LocalDateTime modifiedAt = LocalDateTime.of(2019,02,04,10,30);
        Position position1 = new Position(1,"First position",0);
        Position position2 = new Position(2,"Second position",3);
        Set<Position> positions = new HashSet<>(Arrays.asList(position1,position2));

        return new Poll(title,createdAt,modifiedAt,positions);
    }
}