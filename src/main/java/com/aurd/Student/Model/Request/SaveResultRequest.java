package com.aurd.Student.Model.Request;

public class SaveResultRequest {

    int stud_id;
    int  inst_id;
    int quiz_id;
    int total_marks;
    int marks_obtained;
    int correct_ans;
    int wrong_ans;
    int cut_off;
    int negative_marks;


    public int getStud_id() {
        return stud_id;
    }

    public void setStud_id(int stud_id) {
        this.stud_id = stud_id;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public int getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(int total_marks) {
        this.total_marks = total_marks;
    }

    public int getMarks_obtained() {
        return marks_obtained;
    }

    public void setMarks_obtained(int marks_obtained) {
        this.marks_obtained = marks_obtained;
    }

    public int getCorrect_ans() {
        return correct_ans;
    }

    public void setCorrect_ans(int correct_ans) {
        this.correct_ans = correct_ans;
    }

    public int getWrong_ans() {
        return wrong_ans;
    }

    public void setWrong_ans(int wrong_ans) {
        this.wrong_ans = wrong_ans;
    }

    public int getCut_off() {
        return cut_off;
    }

    public void setCut_off(int cut_off) {
        this.cut_off = cut_off;
    }

    public int getNegative_marks() {
        return negative_marks;
    }

    public void setNegative_marks(int negative_marks) {
        this.negative_marks = negative_marks;
    }
}
