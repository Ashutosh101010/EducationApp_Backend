package com.aurd.Student.Model.BeanClass;

import com.aurd.Student.Model.Entity.Question_Option_Model;

import java.util.ArrayList;

public class SolutionEntity {
    String question;
    String myAnswer;
    String answer;
    ArrayList<Question_Option_Model> options = new ArrayList<>();

    public ArrayList<Question_Option_Model> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Question_Option_Model> options) {
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(String myAnswer) {
        this.myAnswer = myAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
