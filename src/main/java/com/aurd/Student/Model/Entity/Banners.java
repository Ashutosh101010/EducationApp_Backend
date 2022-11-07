package com.aurd.Student.Model.Entity;

import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;

@Entity(name = "banners")
public class Banners {

    @Id
    @GeneratedValue
    private Long id;
    private String banner;
    @Column(name = "inst_id")
    private Long instId;

    private String type;
    @Column(name = "type_id")
    private Long typeId;



    @ManyToOne
   
    Institute institute;

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }


    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
