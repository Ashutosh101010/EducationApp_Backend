package com.aurd.Student.Model.Entity.map;


import com.aurd.Student.Model.Entity.SubjectModel;

import javax.persistence.*;
import javax.validation.constraints.Max;


@Entity
@Table(name = "quiz_ques_map")
public class Quiz_Question_Map_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;



    @Column(name = "quiz_id",nullable = false)
    private long quiz_id;


    @Column(name = "ques_id",nullable = false)
    private long ques_id;

    @Column(name = "marks",nullable = false)
    @Max(12)
    private  long marks;

    @Column(name = "subject_id",nullable = false)
    @Max(12)
    private long subject_id;

//    @Column(name = "updated_on",nullable = false)
//    private Timestamp updated_on;

    @Column(name = "updated_by",nullable = false)
    @Max(12)
    private long updated_by;

    @ManyToOne
    @JoinColumn(name="subject_id", nullable=false,insertable = false,updatable = false)
    private SubjectModel subjectModel;


    public SubjectModel getSubjectModel() {
        return subjectModel;
    }

    public void setSubjectModel(SubjectModel subjectModel) {
        this.subjectModel = subjectModel;
    }

    public long getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(long quiz_id) {
        this.quiz_id = quiz_id;
    }

    public long getQues_id() {
        return ques_id;
    }

    public void setQues_id(long ques_id) {
        this.ques_id = ques_id;
    }

    public long getMarks() {
        return marks;
    }

    public void setMarks(long marks) {
        this.marks = marks;
    }

    public long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(long subject_id) {
        this.subject_id = subject_id;
    }

//    public Timestamp getUpdated_on() {
//        return updated_on;
//    }
//
//    public void setUpdated_on(Timestamp updated_on) {
//        this.updated_on = updated_on;
//    }

    public long getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(long updated_by) {
        this.updated_by = updated_by;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
