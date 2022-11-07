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

public class BookMark {

  @Id
  @GeneratedValue
  private Long id;
  @Column(name = "post_id")
  private Long postId;
  @Column(name = "added_by")
  private Long addedBy;
  @Column(name = "added_on")
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
  private  Timestamp addedOn;

  private String type;


  @ManyToOne
  @JoinColumn(name = "added_by",insertable = false,updatable = false)
  Student student;

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getId() {
    return id;
  }


  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }


  public Long getAddedBy() {
    return addedBy;
  }

  public void setAddedBy(Long addedBy) {
    this.addedBy = addedBy;
  }


  public Timestamp getAddedOn() {
    return addedOn;
  }

  public void setAddedOn(Timestamp addedOn) {
    this.addedOn = addedOn;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
