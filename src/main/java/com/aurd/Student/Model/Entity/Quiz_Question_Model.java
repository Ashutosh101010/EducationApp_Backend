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
public class Quiz_Question_Model {

    @Id

    @Column(name = "question_id",nullable = false)
    @PrimaryKeyJoinColumn
    private  long question_id;

    @Column(name = "question_type",nullable = false)
    @NotBlank
    @Max(20)
    private String question_type;

    @Column(name = "question",nullable = false)
    @NotBlank
    @Max(1001)
    private  String question;

    @Column(name = "marks",nullable = false)
    private  long marks;

    @Column(name = "answer",nullable = false)
    @NotBlank
    @Max(1001)
    private String answer;

    @Column(name = "ans_description",nullable = true)
    @Nullable
    private String ans_description;

    @Column(name = "pic",nullable = true)
    @Nullable
    private  String pic;

    @Column(name = "added_on",nullable = false)
    private Timestamp added_on;

    @Column(name = "added_by",nullable = false)
    private long added_by;

    @Column(name = "updated_by",nullable = false)
    private long updated_by;

    @Column(name = "inst_id",nullable = false)
    private long inst_id;

    @Column(name = "subject_id",nullable = false)
    private  long subject_id;


    @ManyToOne
    @JoinColumn(name="subject_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    SubjectModel subjectModel;

    public SubjectModel getSubjectModel() {
        return subjectModel;
    }

    public void setSubjectModel(SubjectModel subjectModel) {
        this.subjectModel = subjectModel;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public long getMarks() {
        return marks;
    }

    public void setMarks(long marks) {
        this.marks = marks;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAns_description() {
        return ans_description;
    }

    public void setAns_description(String ans_description) {
        this.ans_description = ans_description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public long getAdded_by() {
        return added_by;
    }

    public void setAdded_by(long added_by) {
        this.added_by = added_by;
    }

    public long getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(long updated_by) {
        this.updated_by = updated_by;
    }

    public long getInst_id() {
        return inst_id;
    }

    public void setInst_id(long inst_id) {
        this.inst_id = inst_id;
    }

    public long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(long subject_id) {
        this.subject_id = subject_id;
    }
}
