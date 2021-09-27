package com.aurd.Student.Model.Entity;

import javax.persistence.*;

@Entity(name = "banners")
public class Banners {

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "banner",nullable = false)
    private String banner;

    @Column(name = "inst_id",nullable = false)
    private long inst_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public long getInst_id() {
        return inst_id;
    }

    public void setInst_id(long inst_id) {
        this.inst_id = inst_id;
    }
}
