package com.votingball.votingball.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "positions")
public class Position {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order")
    private int order;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "positions")
    List<Poll> polls;

    public Position() {
    }

    public Position(int order, String name) {
        this.order = order;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Poll> getPolls() {
        return polls;
    }

    public void setPolls(List<Poll> polls) {
        this.polls = polls;
    }
}
