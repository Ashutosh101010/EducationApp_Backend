package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "institute_master")

public class Institute_Master_Model {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name="institute", nullable = false)
        @NotBlank
        @Size(max = 256)
        private String institute;


        @Column(name="address",nullable = true)
        private String address;

        @Column(name = "city_id",nullable = true)
        private  int city_id;

        @Column(name = "state_id",nullable = true)
        private int state_id;


        @Column(name = "district",nullable = true)
        private  String district;

        @Column(name="email",nullable = false)
        @NotBlank
        private String email;


        @Column(name="contact",nullable = true)
        private  String contact;


        @Column(name="logo",nullable = false)
        @NotBlank
        @Size(max = 250)
        private  String logo;

        @Column(name="domain",nullable = true)
        private  String domain;

        @Column(name = "library",nullable = true)
        private String library;

       @Column(name = "libraryFee",nullable = false)
        private long libraryFee;

       @Column(name = "hostelFee",nullable = false)
        private long hostelFee;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getInstitute() {
                return institute;
        }

        public void setInstitute(String institute) {
                this.institute = institute;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public int getCity_id() {
                return city_id;
        }

        public void setCity_id(int city_id) {
                this.city_id = city_id;
        }

        public int getState_id() {
                return state_id;
        }

        public void setState_id(int state_id) {
                this.state_id = state_id;
        }

        public String getDistrict() {
                return district;
        }

        public void setDistrict(String district) {
                this.district = district;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getContact() {
                return contact;
        }

        public void setContact(String contact) {
                this.contact = contact;
        }

        public String getLogo() {
                return logo;
        }

        public void setLogo(String logo) {
                this.logo = logo;
        }

        public String getDomain() {
                return domain;
        }

        public void setDomain(String domain) {
                this.domain = domain;
        }

        public String getLibrary() {
        return library;
        }

       public void setLibrary(String library) {
        this.library = library;
       }

          public long getLibraryFee() {
                return libraryFee;
        }

        public void setLibraryFee(long libraryFee) {
                this.libraryFee = libraryFee;
        }

        public long getHostelFee() {
                return hostelFee;
        }

        public void setHostelFee(long hostelFee) {
                this.hostelFee = hostelFee;
        }
}
