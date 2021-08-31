package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "student_posts")

public class BookMarkModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id",nullable = false)
    @NotBlank
    private long id;

    @Column(name = "discription",nullable = false)
    @NotBlank
    private String description;

    @Column(name = "pic",nullable = false)
    @NotBlank
    private String pic;

    @Column(name = "added_by",nullable = false)
    @NotBlank
    private int added_by;

    @Column(name ="post_status",nullable = false)
    @NotBlank
    private int post_status;

    @Column(name ="added_on",nullable = false)
    @NotBlank
    private Timestamp added_on;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public int getAdded_by() {
    return added_by;
  }

  public void setAdded_by(int added_by) {
    this.added_by = added_by;
  }

  public  int getPost_status(){
    return post_status;
  }

  public void setPost_status(int post_status) {
    this.post_status = post_status;
  }

  public Timestamp getAdded_on() {
    return added_on;
  }

  public void setAdded_on(Timestamp added_on) {
    this.added_on = added_on;
  }
}
