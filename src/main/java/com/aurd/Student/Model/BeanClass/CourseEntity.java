package com.aurd.Student.Model.BeanClass;



import java.util.ArrayList;

public class CourseEntity {
    private long id;
    private String course;
    private String description;
    private String logo;
    private int duration;
    private double fee;
    private Integer runningBatch;
    private Integer domainId;
    private long inst_id;
    private int course_active;
    private String banner_img;
    private String language;
//    private ArrayList<RunningBatchesModel> batchList = new ArrayList<>();
    private boolean purchased ;

    private Long notesCount;
    private Long videoCount;
    private Long practiseTestCount;

    private String banner_url;
    private String url_content_type;
    private String intro_video;

    public String getIntro_video() {
        return intro_video;
    }

    public void setIntro_video(String intro_video) {
        this.intro_video = intro_video;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }

    public String getUrl_content_type() {
        return url_content_type;
    }

    public void setUrl_content_type(String url_content_type) {
        this.url_content_type = url_content_type;
    }

    public Long getPractiseTestCount() {
        return practiseTestCount;
    }

    public void setPractiseTestCount(Long practiseTestCount) {
        this.practiseTestCount = practiseTestCount;
    }


    public Long getNotesCount() {
        return notesCount;
    }

    public void setNotesCount(Long notesCount) {
        this.notesCount = notesCount;
    }

    public Long getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Long videoCount) {
        this.videoCount = videoCount;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Integer getRunningBatch() {
        return runningBatch;
    }

    public void setRunningBatch(Integer runningBatch) {
        this.runningBatch = runningBatch;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    public long getInst_id() {
        return inst_id;
    }

    public void setInst_id(long inst_id) {
        this.inst_id = inst_id;
    }

    public int getCourse_active() {
        return course_active;
    }

    public void setCourse_active(int course_active) {
        this.course_active = course_active;
    }

    public String getBanner_img() {
        return banner_img;
    }

    public void setBanner_img(String banner_img) {
        this.banner_img = banner_img;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

//    public ArrayList<RunningBatchesModel> getBatchList() {
//        return batchList;
//    }
//
//    public void setBatchList(ArrayList<RunningBatchesModel> batchList) {
//        this.batchList = batchList;
//    }
}
