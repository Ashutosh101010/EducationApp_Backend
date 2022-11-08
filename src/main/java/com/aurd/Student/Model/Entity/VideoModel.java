package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.sql.Timestamp;

@Entity
@Table(name = "videos")
public class VideoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "name",nullable = false)
    @Max(256)
    private String name;

    @Column(name = "video",nullable = false)
    @Max(256)
    private  String video;

    @Column(name = "thumb",nullable = false)
    @Max(256)
    private  String thumb;

    @Column(name = "topicId",nullable = false)
    private  Integer topicId;

    @Column(name = "created_at",nullable = false)
    private Timestamp created_at;

    @Column(name = "created_by",nullable = false)
    private long created_by;

    @Column(name = "description",nullable = true)
    private String description;



//    @Column(name = "video_type",nullable = false)
//    private String video_type;


    @Column(name = "fee_type",nullable = true)
    private String fee_type;

    @Column(name = "course_id",nullable = true)
    private Long course_id;

    @Column(name = "sub_subject_id",nullable = true)
    private Integer sub_subject_id;

    @Column(name = "subject_id",nullable = true)
    private Integer subject_id;


    @Column(name = "inst_id",nullable = false)
    private Integer inst_id;

    private String ldVideo;
    private String sdVideo;
    private String hdVideo;


    public String getLdVideo() {
        return ldVideo;
    }

    public void setLdVideo(String ldVideo) {
        this.ldVideo = ldVideo;
    }

    public String getSdVideo() {
        return sdVideo;
    }

    public void setSdVideo(String sdVideo) {
        this.sdVideo = sdVideo;
    }

    public String getHdVideo() {
        return hdVideo;
    }

    public void setHdVideo(String hdVideo) {
        this.hdVideo = hdVideo;
    }

    public Integer getInst_id() {
        return inst_id;
    }

    public void setInst_id(Integer inst_id) {
        this.inst_id = inst_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Integer getSub_subject_id() {
        return sub_subject_id;
    }

    public void setSub_subject_id(Integer sub_subject_id) {
        this.sub_subject_id = sub_subject_id;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
    }


    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }


    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(long created_by) {
        this.created_by = created_by;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


//    public String getVideo_type() {
//        return video_type;
//    }
//
//    public void setVideo_type(String video_type) {
//        this.video_type = video_type;
//    }
}
