package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "videos")

public class LiveClassModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "name",nullable = false)
    @NotBlank
    @Max(256)
    private String name;


    @Column(name = "video",nullable = false)
    @NotBlank
    @Max(256)
    private String video;


    @Column(name = "thumb",nullable = false)
    @NotBlank
    @Max(256)
    private String thumb;

    @Column(name = "topicId",nullable = false)
    private int topicId;


    @Column(name = "inst_id",nullable = false)
    private int inst_id;

    @Column(name = "teacher_id",nullable = false)
    private int teacher_id;

    @Column(name = "description",nullable = true)
    @Max(300)
    private String description;

    @Column(name = "tags",nullable = true)
    @Max(100)
    private String tags;

    @Column(name = "video_type",nullable = false)
    @NotBlank
    private  String video_type;








}
