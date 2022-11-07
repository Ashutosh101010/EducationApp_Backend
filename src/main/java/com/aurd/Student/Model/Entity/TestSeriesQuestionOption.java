package com.aurd.Student.Model.Entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "question_option_master_for_series")
public class TestSeriesQuestionOption {


    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "question_id", nullable = false)
    private Long question_id;


    @Column(name = "option_description", nullable = false)
    private String optionDescription;

    @Column(name = "option_discription_with_images", nullable = false)
    private String option_discription_with_images;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }

    public String getOption_discription_with_images() {
        return option_discription_with_images;
    }

    public void setOption_discription_with_images(String option_discription_with_images) {
        this.option_discription_with_images = option_discription_with_images;
    }
}

