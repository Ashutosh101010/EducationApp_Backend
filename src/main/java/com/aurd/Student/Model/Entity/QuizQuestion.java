package com.aurd.Student.Model.Entity;

import io.smallrye.common.constraint.Nullable;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "quiz_question_master")
public class QuizQuestion {

    @Id
    @Column(name = "question_id",nullable = false)
    private  Long question_id;

    @Column(name = "question_type",nullable = false)
    private String questionType;

    @Column(name = "question",nullable = false)
    private  String question;

    private  Integer marks;

    @Column(name = "answer",nullable = false)
    private String answer;

    @Column(name = "ans_description")
    private String ansDescription;

    @Column(name = "added_on",nullable = false)
    private Timestamp addedOn;

    @Column(name = "added_by",nullable = false)
    private Long addedBy;

    @Column(name = "updated_by",nullable = false)
    private Long updatedBy;

    @Column(name = "inst_id",nullable = false)
    private Long instId;

    @Column(name = "subject_id",nullable = false)
    private  Long subjectId;


    @ManyToOne
    @JoinColumn(name="subject_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    Subject subject;

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnsDescription() {
        return ansDescription;
    }

    public void setAnsDescription(String ansDescription) {
        this.ansDescription = ansDescription;
    }

    public Timestamp getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Timestamp addedOn) {
        this.addedOn = addedOn;
    }

    public Long getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Long addedBy) {
        this.addedBy = addedBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
