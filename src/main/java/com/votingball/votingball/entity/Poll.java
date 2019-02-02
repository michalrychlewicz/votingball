package com.votingball.votingball.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "polls")
public class Poll {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "creationLocalDateTime")
    private LocalDateTime creationLocalDateTime;

    @Column(name = "lastModification")
    private LocalDateTime lastModification;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "poll_positions",
            joinColumns = { @JoinColumn(name = "poll_id") },
            inverseJoinColumns = { @JoinColumn(name = "position_id") }
    )
    private List<Position> positions;

    public Poll() {
    }

    public Poll(String title, LocalDateTime creationLocalDateTime, LocalDateTime lastModification) {
        this.title = title;
        this.creationLocalDateTime = creationLocalDateTime;
        this.lastModification = lastModification;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreationLocalDateTime() {
        return creationLocalDateTime;
    }

    public void setCreationLocalDateTime(LocalDateTime creationLocalDateTime) {
        this.creationLocalDateTime = creationLocalDateTime;
    }

    public LocalDateTime getLastModification() {
        return lastModification;
    }

    public void setLastModification(LocalDateTime lastModification) {
        this.lastModification = lastModification;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
