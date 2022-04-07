package com.aurd.Student.Model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "youtubeLiveSession")
public class YoutubeLiveSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "url")
    String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
