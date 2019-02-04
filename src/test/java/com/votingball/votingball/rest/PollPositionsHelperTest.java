package com.votingball.votingball.rest;

import com.votingball.votingball.entity.Poll;
import com.votingball.votingball.entity.Position;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PollPositionsHelperTest {

    @Test
    public void shouldFillPositionsIdsWithPollId_whenSuchArePassed()
    {
        // given
        Position position1 = new Position(1,"name_1",0);
        Position position2 = new Position(2,"name_2",0);
        Set<Position> positions = new HashSet<>(Arrays.asList(position1,position2));
        Poll poll = new Poll("title",positions);
        poll.setId(5);
        PollPositionsHelper pollPositionsHelper = new PollPositionsHelper();
        //when
        pollPositionsHelper.setPositionsReferenceToPoll(poll,poll.getPositions());

        //then
        for(Position position : poll.getPositions())
        {
            assertThat(position.getPoll(),is(poll));
        }
    }
}