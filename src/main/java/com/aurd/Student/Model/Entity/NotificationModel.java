package com.aurd.Student.Model.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "notification")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notification_id;
    private String notification_title;
    private String notification_body;
    private int sender_id;
    private int receiver_id;
    private String sender_type;
    private Timestamp time;
    private int inst_id;
    private String type;
    private Long entityId;
    @Transient
    BlogModel blog;
    @Transient
    CurrentAffairModel currentAffair;
    @Transient
    VideoModel video;
    @Transient
    NotesModel notes;

    public BlogModel getBlog() {
        return blog;
    }

    public void setBlog(BlogModel blog) {
        this.blog = blog;
    }

    public CurrentAffairModel getCurrentAffair() {
        return currentAffair;
    }

    public void setCurrentAffair(CurrentAffairModel currentAffair) {
        this.currentAffair = currentAffair;
    }

    public VideoModel getVideo() {
        return video;
    }

    public void setVideo(VideoModel video) {
        this.video = video;
    }

    public NotesModel getNotes() {
        return notes;
    }

    public void setNotes(NotesModel notes) {
        this.notes = notes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public int getInst_id() {
        return inst_id;
    }

    public void setInst_id(int inst_id) {
        this.inst_id = inst_id;
    }

    public int getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(int notification_id) {
        this.notification_id = notification_id;
    }

    public String getNotification_title() {
        return notification_title;
    }

    public void setNotification_title(String notification_title) {
        this.notification_title = notification_title;
    }

    public String getNotification_body() {
        return notification_body;
    }

    public void setNotification_body(String notification_body) {
        this.notification_body = notification_body;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getSender_type() {
        return sender_type;
    }

    public void setSender_type(String sender_type) {
        this.sender_type = sender_type;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
