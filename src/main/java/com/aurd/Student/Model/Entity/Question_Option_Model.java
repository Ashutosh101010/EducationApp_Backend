package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "question_option_master")
public class Question_Option_Model {

    @Id
    @PrimaryKeyJoinColumn
    private long option_id;

    @Column(name = "question_id",nullable = false)
    private Long question_id;


    @Column(name = "option_discription",nullable = false)
    @NotBlank
    private String option_discription;

    public long getOption_id() {
        return option_id;
    }

    public void setOption_id(long option_id) {
        this.option_id = option_id;
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public String getOption_discription() {
        return option_discription;
    }

    public void setOption_discription(String option_discription) {
        this.option_discription = option_discription;
    }
}
