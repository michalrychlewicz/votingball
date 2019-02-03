package com.votingball.votingball.rest;

import com.votingball.votingball.entity.Poll;
import com.votingball.votingball.entity.Position;

import java.util.Set;

public class PollPositionsHelper {

    public void setPositionsReferenceToPoll(Poll poll, Set<Position> positions) {
      if(poll == null || positions == null)
      {
          return;
      }
      for(Position position : positions)
      {
          position.setPoll(poll);
      }
    }

}
