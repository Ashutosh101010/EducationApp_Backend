package com.aurd.Student.Model.BeanClass;

import com.aurd.Student.Model.Entity.Question_Option_Model;

import java.sql.Timestamp;
import java.util.ArrayList;


public class QuizQuestionEntity {

    String question;
    long ques_id;
    String question_type;
    long marks;
    String answer;
    String ans_description;
    String pic;
    Timestamp added_on;
    long quiz_id;
    ArrayList options = new ArrayList<>();
    long subject_id;
    long option_id;
    String option_discription;
    String subject;

    private String question_with_images;

    public String getQuestion_with_images() {
        return question_with_images;
    }

    public void setQuestion_with_images(String question_with_images) {
        this.question_with_images = question_with_images;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(long subject_id) {
        this.subject_id = subject_id;
    }

    public long getOption_id() {
        return option_id;
    }

    public void setOption_id(long option_id) {
        this.option_id = option_id;
    }

    public String getOption_discription() {
        return option_discription;
    }

    public void setOption_discription(String option_discription) {
        this.option_discription = option_discription;
    }

    public ArrayList<Question_Option_Model> getOptions() {
        return options;
    }

    public void setOptions(ArrayList options) {
        this.options = options;
    }

    public long getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(long quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public long getQues_id() {
        return ques_id;
    }

    public void setQues_id(long ques_id) {
        this.ques_id = ques_id;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
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






}
