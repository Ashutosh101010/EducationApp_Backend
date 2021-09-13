package com.aurd.Student.Model.Entity;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "current_affairs_liked")

public class Current_AffairsLiked_Model {
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "current_affair_id")
    private int current_affair_id;

    @Column(name = "added_by")
    private int added_by;

    @Column(name = "added_on")
    private Timestamp added_on;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrent_affair_id() {
        return current_affair_id;
    }

    public void setCurrent_affair_id(int current_affair_id) {
        this.current_affair_id = current_affair_id;
    }

    public int getAdded_by() {
        return added_by;
    }

    public void setAdded_by(int added_by) {
        this.added_by = added_by;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }
}
