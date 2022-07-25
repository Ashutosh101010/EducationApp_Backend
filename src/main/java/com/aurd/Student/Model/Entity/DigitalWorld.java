package com.aurd.Student.Model.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "digital_world_folder")
public class DigitalWorld {

    private Long id;
    private Integer inst_id;
    private String name;

    public Integer getInst_id() {
        return inst_id;
    }

    public void setInst_id(Integer inst_id) {
        this.inst_id = inst_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }


}
