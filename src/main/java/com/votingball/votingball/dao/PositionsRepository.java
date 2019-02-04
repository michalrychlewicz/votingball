package com.votingball.votingball.dao;

import com.votingball.votingball.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Michal Rychlewicz
 */
@Repository
public interface PositionsRepository extends JpaRepository<Position,Integer> {
}
