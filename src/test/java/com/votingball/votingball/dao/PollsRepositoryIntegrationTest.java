package com.votingball.votingball.dao;

import com.votingball.votingball.entity.Poll;
import com.votingball.votingball.entity.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;

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

    @Autowired
    private PositionsRepository positionsRepository;


    @Test
    public void whenFindById_thenReturnPoll() {
        // given
        Poll pollBeingInserted = preparePollForInsertion("pollTitle");
        Poll persistedPoll = entityManager.persist(pollBeingInserted);
        entityManager.flush();
        int pollId = persistedPoll.getId();

        // when
        Optional<Poll> retrievedPoll = employeeRepository.findById(pollId);
        assertThat("Poll was just deleted. It can not exist in database",retrievedPoll.isPresent(),is(true));

        // then
        Poll actualPoll = retrievedPoll.get();
        assertThat(actualPoll,is(equalTo(pollBeingInserted)));
    }

    @Test
    public void whenDeletePollFromDatabase_thenDatabaseShouldNotContainThatPollAndItsPositionsAndOtherOnesAreUntouched() {
        // given
        Poll pollBeingInserted = preparePollForInsertion("pollTitle");
        Poll persistedPoll = entityManager.persist(pollBeingInserted);
        Poll otherPoll = preparePollForInsertion("pollTittle2");
        Poll persistedOtherPoll = entityManager.persist(otherPoll);
        entityManager.flush();
        int pollId = persistedPoll.getId();
        int otherPollId = persistedOtherPoll.getId();
        Set<Position> persistedPollPositions = persistedPoll.getPositions();
        List<Position> persistedOtherPollPositions = new ArrayList<>(persistedOtherPoll.getPositions());

        // when
        employeeRepository.delete(persistedPoll);

        Optional<Poll> retrievedPoll = employeeRepository.findById(pollId);
        List<Position> retrievedPositions = getAllExistingCorrespondingPositionsByTheirIds(persistedPollPositions);
        Optional<Poll> retrievedOtherPoll = employeeRepository.findById(otherPollId);
        List<Position> retrievedOtherPositions = getAllExistingCorrespondingPositionsByTheirIds(persistedOtherPollPositions);

        // then
        assertThat("Poll was just deleted. It can not exist in database",retrievedPoll.isPresent(),is(false));
        assertThat("Positions should be cascade removed",retrievedPositions.isEmpty(),is(true));
        assertThat(retrievedOtherPoll.get(),is(equalTo(persistedOtherPoll)));
        assertThat(retrievedOtherPositions,equalTo(persistedOtherPollPositions));
    }
    
    private List<Position> getAllExistingCorrespondingPositionsByTheirIds(Collection<Position> persistedPollPositions) {
        List<Position> retrievedPositions = new ArrayList<>();
        for(Position persistedPosition : persistedPollPositions)
        {
           Optional<Position> retrievedPosition = positionsRepository.findById(persistedPosition.getId());
           if(retrievedPosition.isPresent())
           {
               retrievedPositions.add(retrievedPosition.get());
           }
        }
        return retrievedPositions;
    }

    private Poll preparePollForInsertion(String pollTittle) {
        LocalDateTime createdAt = LocalDateTime.of(2019,02,04,10,30);
        LocalDateTime modifiedAt = LocalDateTime.of(2019,02,04,10,30);
        Position position1 = new Position(1,"First position",0);
        Position position2 = new Position(2,"Second position",3);
        Set<Position> positions = new TreeSet<>(Arrays.asList(position1,position2));

        return new Poll(pollTittle,createdAt,modifiedAt,positions);
    }
}