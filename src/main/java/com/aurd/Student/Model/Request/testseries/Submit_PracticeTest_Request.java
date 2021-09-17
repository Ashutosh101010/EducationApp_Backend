package com.aurd.Student.Model.Request.testseries;

import java.sql.Timestamp;

public class Submit_PracticeTest_Request {

    long inst_id;
    long quiz_id;
    String description;
    String image;
    String pic;
    int is_active;
    String name;
    double price;
    int total;
    Timestamp test_start;
    Timestamp test_end;
    int cutoff;
    String instruction;
    int subject_id;

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public long getInst_id() {
        return inst_id;
    }

    public void setInst_id(long inst_id) {
        this.inst_id = inst_id;
    }

    public long getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(long quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Timestamp getTest_start() {
        return test_start;
    }

    public void setTest_start(Timestamp test_start) {
        this.test_start = test_start;
    }

    public Timestamp getTest_end() {
        return test_end;
    }

    public void setTest_end(Timestamp test_end) {
        this.test_end = test_end;
    }

    public int getCutoff() {
        return cutoff;
    }

    public void setCutoff(int cutoff) {
        this.cutoff = cutoff;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
