package com.aurd.Student.Model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "vimeoLiveSession")
public class VimeoLiveSession {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String vimeoId;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVimeoId() {
            return vimeoId;
        }

        public void setVimeoId(String vimeoId) {
            this.vimeoId = vimeoId;
        }
    }

