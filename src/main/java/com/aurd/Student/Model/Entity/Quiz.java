package com.aurd.Student.Model.Entity;

import com.aurd.Student.Model.BeanClass.TestEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "quiz_master")
public class Quiz{

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "sub_subject_id")

    private Long subSubjectId;


    @Column(name = "course_id",nullable = false)
    private Long courseId;

    private String type;

    @Column(name = "title",nullable = false)
    private String title;

    private  String description;


    private String pic;

    private String price;

    @Column(name = "test_start",nullable = false)
    private Timestamp testStart;



    @Column(name = "test_duration",nullable = true)
    private String testDuration;

    @Column(name = "added_on",nullable = false)
    private Timestamp addedOn;

    @Column(name = "added_by",nullable = false)
    private Long addedBy;

    @Column(name = "updated_by",nullable = false)
    private Long updatedBy;

    @Column(name = "instId",nullable = true)
    private Integer inst_id;


    @Column(name = "is_active",nullable = false)
    private  Boolean is_active;

    @Column(name = "marks_per_ques",nullable = true)
    private Integer marksPerQues;

    @Column(name = "total_ques",nullable = false)
    private  Integer total_ques;


    @Column(name = "cutoff",nullable = true)
    private Integer cutoff;


    private String instruction;

    private Timestamp time;

    @Column(name = "negative_marking",nullable = true)
    private String negativeMarking;

    @Column(name = "quiz_type",nullable = true)
    private String quizType;

    @Column(name = "test_end",nullable = true)
    private Timestamp testEnd;


    @Transient
    boolean testSeriesPurchased;

    @Transient
    List<TestEntity> testList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getSubSubjectId() {
        return subSubjectId;
    }

    public void setSubSubjectId(Long subSubjectId) {
        this.subSubjectId = subSubjectId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Timestamp getTestStart() {
        return testStart;
    }

    public void setTestStart(Timestamp testStart) {
        this.testStart = testStart;
    }

    public String getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(String testDuration) {
        this.testDuration = testDuration;
    }

    public Timestamp getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Timestamp addedOn) {
        this.addedOn = addedOn;
    }

    public Long getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Long addedBy) {
        this.addedBy = addedBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getInst_id() {
        return inst_id;
    }

    public void setInst_id(Integer inst_id) {
        this.inst_id = inst_id;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Integer getMarksPerQues() {
        return marksPerQues;
    }

    public void setMarksPerQues(Integer marksPerQues) {
        this.marksPerQues = marksPerQues;
    }

    public Integer getTotal_ques() {
        return total_ques;
    }

    public void setTotal_ques(Integer total_ques) {
        this.total_ques = total_ques;
    }

    public Integer getCutoff() {
        return cutoff;
    }

    public void setCutoff(Integer cutoff) {
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

    public String getNegativeMarking() {
        return negativeMarking;
    }

    public void setNegativeMarking(String negativeMarking) {
        this.negativeMarking = negativeMarking;
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

    public Timestamp getTestEnd() {
        return testEnd;
    }

    public void setTestEnd(Timestamp testEnd) {
        this.testEnd = testEnd;
    }

    public boolean isTestSeriesPurchased() {
        return testSeriesPurchased;
    }

    public void setTestSeriesPurchased(boolean testSeriesPurchased) {
        this.testSeriesPurchased = testSeriesPurchased;
    }

    public List<TestEntity> getTestList() {
        return testList;
    }

    public void setTestList(List<TestEntity> testList) {
        this.testList = testList;
    }
}
