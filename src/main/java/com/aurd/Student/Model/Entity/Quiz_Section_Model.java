package com.aurd.Student.Model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "quiz_sections")
public class Quiz_Section_Model {

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    @Column(name = "subject_id",nullable = false)
    int subject_id;

    @Column(name = "quiz_id",nullable = false)
    int quiz_id;

    @Column(name = "subject_cutoff",nullable = false)
    int subject_cutoff;

    @Column(name = "subject_total",nullable = false)
    int subject_total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public int getSubject_cutoff() {
        return subject_cutoff;
    }

    public void setSubject_cutoff(int subject_cutoff) {
        this.subject_cutoff = subject_cutoff;
    }

    public int getSubject_total() {
        return subject_total;
    }

    public void setSubject_total(int subject_total) {
        this.subject_total = subject_total;
    }
}



