package com.aurd.Student.Model.Entity;

import com.aurd.Student.Model.Entity.map.Quiz_Question_Map_Model;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
public class SubjectModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "subject",nullable = false)
    private String subject;

    @Column(name ="inst_id",nullable = false)
    private int inst_id;
//
//    @OneToMany(mappedBy = "subject_id")
////    @JoinColumn(name = "subject_id")
//    List<Quiz_Question_Map_Model> quiz_question_map_model;


//    public List<Quiz_Question_Map_Model> getQuiz_question_map_model() {
//        return quiz_question_map_model;
//    }
//
//    public void setQuiz_question_map_model(List<Quiz_Question_Map_Model> quiz_question_map_model) {
//        this.quiz_question_map_model = quiz_question_map_model;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }

    @Override
    public String toString() {
        return "SubjectModel{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", inst_id=" + inst_id +
                '}';
    }
}
