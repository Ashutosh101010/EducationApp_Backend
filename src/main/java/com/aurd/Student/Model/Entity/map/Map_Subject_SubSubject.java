package com.aurd.Student.Model.Entity.map;


import javax.persistence.*;

@Entity
@Table(name = "subject_sub_subjects")
public class Map_Subject_SubSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;


    @Column(name = "subsubId",nullable = false)
    private long subsubId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSubsubId() {
        return subsubId;
    }

    public void setSubsubId(long subsubId) {
        this.subsubId = subsubId;
    }
}
