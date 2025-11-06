package com.istiaq.daycare.dto;

import com.istiaq.daycare.entity.Job;
import java.util.Date;

public class CircularDTO {

    private Long id;
    private String title;
    private String description;
    private Double salary;
    private String jobType;
    private Date postedDate;

    // Category info
    private CategoryDTO category;

    // Location info
    private LocationDTO location;

    // Parent info
    private Long parentId;
    private String parentName;
    private String contactPerson;
    private String email;
    private String phone;
    private String childName;
    private String photo;

    public CircularDTO() {
        // default constructor
    }

    public CircularDTO(Job job) { // Change Job → Circular if your entity is named Circular
        this.id = job.getId();
        this.title = job.getTitle();
        this.description = job.getDescription();
        this.salary = job.getSalary();
        this.jobType = job.getJobType();
        this.postedDate = job.getPostedDate();

        if (job.getCategory() != null) {
            this.category = new CategoryDTO(job.getCategory());
        }

        if (job.getLocation() != null) {
            this.location = new LocationDTO(job.getLocation().getId(), job.getLocation().getName());
        }

        if (job.getParent() != null) {
            this.parentId = job.getParent().getId();
            this.parentName = job.getParent().getParentName();
            this.contactPerson = job.getParent().getContactPerson();
            this.email = job.getParent().getEmail();
            this.phone = job.getParent().getPhone();
            this.childName = job.getParent().getChildName();
            this.photo = job.getParent().getPhoto();
        }
    }

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
