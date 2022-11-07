package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "audio_books_media")
public class AudioBookMedia {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String media;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private Long audioBookId;
    private Long sNo;


//    @ManyToOne
//    private AudioBook audioBook;


    @ManyToOne
    Institute institute;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    @Column(name = "created_on")
    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "updated_on")
    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getAudioBookId() {
        return audioBookId;
    }

    public void setAudioBookId(Long audioBookId) {
        this.audioBookId = audioBookId;
    }

    @Column(name = "s_no")
    public Long getsNo() {
        return sNo;
    }

    public void setsNo(Long sNo) {
        this.sNo = sNo;
    }

//    public AudioBook getAudioBook() {
//        return audioBook;
//    }
//
//    public void setAudioBook(AudioBook audioBook) {
//        this.audioBook = audioBook;
//    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
