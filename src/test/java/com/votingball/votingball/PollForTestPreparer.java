package com.votingball.votingball;

import com.votingball.votingball.entity.Poll;
import com.votingball.votingball.entity.Position;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Michal Rychlewicz
 */
public class PollForTestPreparer {

    public static Poll preparePoll(String pollTittle) {
        LocalDateTime createdAt = LocalDateTime.of(2019,02,04,10,30);
        LocalDateTime modifiedAt = LocalDateTime.of(2019,02,04,10,30);
        Position position1 = new Position(1,"First position",0);
        Position position2 = new Position(2,"Second position",3);
        Set<Position> positions = new TreeSet<>(Arrays.asList(position1,position2));

        return new Poll(pollTittle,createdAt,modifiedAt,positions);
    }
}
