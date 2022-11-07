package com.aurd.Student.Model.Entity.map;

import javax.persistence.*;

@Entity
@Table(name = "subject_topics")
public class SubSubject_Topic {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  Long id;


        @Column(name = "topic_id",nullable = false)
        private Long topicId;
        @Column(name = "subject_id",nullable = false)
        private Long subjectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
