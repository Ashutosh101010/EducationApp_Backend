package com.aurd.Student.Model.Entity;


import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;

@Entity
@Table(name = "report")

public class Report {
    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "quiz_type")
    private String quizType;
    private String feedback;

    @Column(name = "ques_id")
    private Long quesId;

    @Column(name = "quiz_id")
    private Long quizId;

    @Column(name = "test_name")
    private String testName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Long getQuesId() {
        return quesId;
    }

    public void setQuesId(Long quesId) {
        this.quesId = quesId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
