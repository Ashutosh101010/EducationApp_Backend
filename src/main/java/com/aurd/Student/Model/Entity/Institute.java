package com.aurd.Student.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "institute_master")

public class Institute {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String institute;

        private String address;

        @Column(name = "city_id")
        private  int cityId;

        @Column(name = "state_id")
        private int stateId;


        private String email;

        private  String contact;


        private  String logo;

        private  String domain;

        private String library;

       @Column(name = "library_fee",nullable = false)
        private long libraryFee;

       @Column(name = "hostel_fee",nullable = false)
        private long hostelFee;


       @ManyToOne
    @JoinColumn(name = "city_id",insertable = false,updatable = false)
    City city;
       @ManyToOne
    @JoinColumn(name = "state_id",insertable = false,updatable = false)
    State state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
