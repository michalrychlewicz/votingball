package com.votingball.votingball.entity;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "polls")
public class Poll {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "last_modification_date")
    @UpdateTimestamp
    private LocalDateTime lastModification;

    @OneToMany(cascade = { CascadeType.ALL },mappedBy = "poll")
    private Set<Position> positions;

    public Poll() {
    }

    public Poll(String title) {
        this(title,new HashSet<>());
    }

    public Poll(String title, Set<Position> positions) {
        this(title,LocalDateTime.now(),LocalDateTime.now(),positions);
    }

    public Poll(String title, LocalDateTime creationDate, LocalDateTime lastModification, Set<Position> positions) {
        this.title = title;
        this.creationDate = creationDate;
        this.lastModification = lastModification;
        this.positions = positions;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setLastModification(LocalDateTime lastModification) {
        this.lastModification = lastModification;
    }

    public LocalDateTime getLastModification() {
        return lastModification;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

    public Set<Position> getPositions() {
        return new TreeSet<>(positions);
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creationDate=" + creationDate +
                ", lastModification=" + lastModification +
                ", positions=" + positions +
                '}';
    }
}
