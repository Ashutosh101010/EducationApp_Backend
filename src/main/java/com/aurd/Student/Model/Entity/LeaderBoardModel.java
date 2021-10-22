package com.aurd.Student.Model.Entity;

public class LeaderBoardModel {
    int id;
    long stud_id;
    long inst_id;
    double marks_obtained;
    long total_marks;
    String status;
    double percent;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStud_id() {
        return stud_id;
    }

    public void setStud_id(long stud_id) {
        this.stud_id = stud_id;
    }

    public long getInst_id() {
        return inst_id;
    }

    public void setInst_id(long inst_id) {
        this.inst_id = inst_id;
    }

    public double getMarks_obtained() {
        return marks_obtained;
    }

    public void setMarks_obtained(double marks_obtained) {
        this.marks_obtained = marks_obtained;
    }

    public long getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(long total_marks) {
        this.total_marks = total_marks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
