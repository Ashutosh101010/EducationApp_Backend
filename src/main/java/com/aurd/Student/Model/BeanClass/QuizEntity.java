package com.aurd.Student.Model.BeanClass;

import java.sql.Timestamp;

public class QuizEntity
{
    private long quiz_id;
    private Integer subject_id;
    private Integer sub_subject_id;
    private Integer course_id;
    private String type;
    private String title;
    private  String discription;
    private String pic;
    private String price;
    private Timestamp test_start;
    private String test_duration;
    private Timestamp added_on;
    private long added_by;
    private long updated_by;
    private Integer inst_id;
    private  int is_active;
    private Integer marks_per_ques;
    private  Integer total_ques;
    private Long cutoff;
    private String instruction;
    private Timestamp time;
    private String negative_marking;
    private String quiz_type;
    private String test_end;
    private  boolean attempt;
    private long indexId;

    private long timeStamp;
    private boolean purchased ;

    private  Long timestamp;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getIndexId() {
        return indexId;
    }

    public void setIndexId(long indexId) {
        this.indexId = indexId;
    }

    public boolean isAttempt() {
        return attempt;
    }

    public void setAttempt(boolean attempt) {
        this.attempt = attempt;
    }

    public long getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(long quiz_id) {
        this.quiz_id = quiz_id;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }

    public Integer getSub_subject_id() {
        return sub_subject_id;
    }

    public void setSub_subject_id(Integer sub_subject_id) {
        this.sub_subject_id = sub_subject_id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Timestamp getTest_start() {
        return test_start;
    }

    public void setTest_start(Timestamp test_start) {
        this.test_start = test_start;
    }

    public String getTest_duration() {
        return test_duration;
    }

    public void setTest_duration(String test_duration) {
        this.test_duration = test_duration;
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

    public Integer getInst_id() {
        return inst_id;
    }

    public void setInst_id(Integer inst_id) {
        this.inst_id = inst_id;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public Integer getMarks_per_ques() {
        return marks_per_ques;
    }

    public void setMarks_per_ques(Integer marks_per_ques) {
        this.marks_per_ques = marks_per_ques;
    }

    public Integer getTotal_ques() {
        return total_ques;
    }

    public void setTotal_ques(Integer total_ques) {
        this.total_ques = total_ques;
    }

    public Long getCutoff() {
        return cutoff;
    }

    public void setCutoff(Long cutoff) {
        this.cutoff = cutoff;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getNegative_marking() {
        return negative_marking;
    }

    public void setNegative_marking(String negative_marking) {
        this.negative_marking = negative_marking;
    }

    public String getQuiz_type() {
        return quiz_type;
    }

    public void setQuiz_type(String quiz_type) {
        this.quiz_type = quiz_type;
    }

    public String getTest_end() {
        return test_end;
    }

    public void setTest_end(String test_end) {
        this.test_end = test_end;
    }
}
