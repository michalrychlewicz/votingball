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

    @Column(name = "position_order")
    private int positionOrder;

    @Column(name = "position_name")
    private String positionName;

    @Column(name = "votes_count")
    private int votesCount;

    public Position() {
    }

    public Position(int positionOrder, String positionName, int votesCount) {
        this(null, positionOrder, positionName, votesCount);
    }

    public Position(Poll poll, int positionOrder, String positionName, int votesCount) {
        this.poll = poll;
        this.positionOrder = positionOrder;
        this.positionName = positionName;
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

    public int getPositionOrder() {
        return positionOrder;
    }

    public void setPositionOrder(int positionOrder) {
        this.positionOrder = positionOrder;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }


    public int getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", positionOrder=" + positionOrder +
                ", positionName=" + positionName +
                ", votesCount=" + votesCount + ", "+
                getPollInformation(poll) + "}";
    }

    @Override
    public int compareTo(Position position) {
        if (positionOrder == position.positionOrder) {
            return 0;
        }
        return positionOrder < position.positionOrder ? -1 : 1;
    }



    private String getPollInformation(Poll poll) {
        return poll != null ? "Poll{id= = " + poll.getId() + ", title=" + poll.getTitle()+"}" : "poll = null";
    }
}
