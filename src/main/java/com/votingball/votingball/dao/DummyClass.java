package com.votingball.votingball.dao;

import com.votingball.votingball.entity.Poll;
import com.votingball.votingball.entity.Position;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DummyClass {

    @Autowired
    private EntityManager entityManager;

    public List<Poll> findAll()
    {
      return Arrays.asList(new Poll("dummy"));
    }

    @Transactional
    public void addDummyData()
    {
        Session session = entityManager.unwrap(Session.class);
        Set<Position> positions = new HashSet<>();
        positions.add(new Position(2,"Pomarańcza",0));
        positions.add(new Position(3,"Mandarynka",0));
        positions.add(new Position(1,"Jabłko",0));
        Poll poll = new Poll("Ulubiony owoc",positions);

        session.save(poll);
    }
}
