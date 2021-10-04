package com.aurd.Student.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.smallrye.common.constraint.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name = "student_posts_saved")

public class BookMarkModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "post_id",nullable = false)
  @NotNull
  private long post_id;

  @Column(name = "added_by",nullable = false)
  @NotNull
  private long added_by;

  @Column(name = "added_on",nullable = true)
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
  private  Timestamp added_on;

  @Column(name = "type",nullable = false)
  private String type;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public long getPost_id() {
    return post_id;
  }

  public void setPost_id(long post_id) {
    this.post_id = post_id;
  }

  public long getAdded_by() {
    return added_by;
  }

  public void setAdded_by(long added_by) {
    this.added_by = added_by;
  }

  public Timestamp getAdded_on() {
    return added_on;
  }

  public void setAdded_on(Timestamp added_on) {
    this.added_on = added_on;
  }
}
