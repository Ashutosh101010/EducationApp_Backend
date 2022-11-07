package com.aurd.Student.Model.BeanClass;



import com.aurd.Student.Model.Entity.QuestionOption;

import java.util.ArrayList;

public class SolutionEntity {
    String question;
    String myAnswer;
    String answer;
    String description;
    ArrayList<QuestionOption> options = new ArrayList<>();
    int markForReview =0;
    int skipped = 0;
    String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSkipped() {
        return skipped;
    }

    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }

    public int getMarkForReview() {
        return markForReview;
    }

    public void setMarkForReview(int markForReview) {
        this.markForReview = markForReview;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<QuestionOption> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<QuestionOption> options) {
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

    @Override
    public String toString() {
        return "SolutionEntity{" +
                "question='" + question + '\'' +
                ", myAnswer='" + myAnswer + '\'' +
                ", answer='" + answer + '\'' +
                ", description='" + description + '\'' +
                ", options=" + options +
                ", markForReview=" + markForReview +
                ", skipped=" + skipped +
                ", subject='" + subject + '\'' +
                '}';
    }
}
