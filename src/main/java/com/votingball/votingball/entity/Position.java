package com.votingball.votingball.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "positions")
public class Position implements Comparable<Position> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    @JsonIgnore
    private Poll poll;

    @Column(name = "order")
    private int order;

    @Column(name = "name")
    private String name;

    @Column(name = "votes_count")
    private int votesCount;

    public Position() {
    }

    public Position(int order, String name, int votesCount) {
        this.order = order;
        this.name = name;
        this.votesCount = votesCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", order=" + order +
                ", name='" + name + '\'' +
                ", votesCount=" + votesCount +
                '}';
    }

    public int getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }

    @Override
    public int compareTo(Position position) {
        if(order == position.order)
        {
            return 0;
        }
        return order < position.order ? -1 : 1;
    }
}
