package com.votingball.votingball.dao;

import com.votingball.votingball.entity.Poll;
import com.votingball.votingball.entity.Position;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

@Repository
public class PollsDaoHibernateImpl implements PollsDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Poll> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("FROM Poll",Poll.class);
        return query.getResultList();
    }

    @Override
    public Poll findById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Poll poll = session.get(Poll.class,id);
        return poll;
    }

    @Override
    public void save(Poll poll) {
        Session session = entityManager.unwrap(Session.class);
        session.save(poll);
    }

    @Override
    public void update(Poll poll) {
        Session session = entityManager.unwrap(Session.class);
        session.save(poll);
    }

    @Override
    public void deleteById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("DELETE from Poll where id=:id",Poll.class);
        query.setParameter("id",id);
        query.executeUpdate();
    }


}
