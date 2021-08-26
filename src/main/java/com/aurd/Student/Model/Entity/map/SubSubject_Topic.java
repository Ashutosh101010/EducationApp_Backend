package com.aurd.Student.Model.Entity.map;

import javax.persistence.*;

@Entity
@Table(name = "subject_topics")
public class SubSubject_Topic {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  long id;


        @Column(name = "topicId",nullable = false)
        private long topicId;
        @Column(name = "subjectId",nullable = false)
        private long subjectId;

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

            public long getTopicId() {
                return topicId;
            }

            public void setTopicId(long topicId) {
                this.topicId = topicId;
            }
}
