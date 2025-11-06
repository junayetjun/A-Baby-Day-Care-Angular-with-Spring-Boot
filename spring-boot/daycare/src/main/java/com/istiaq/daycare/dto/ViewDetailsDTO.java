package com.istiaq.daycare.dto;

import com.istiaq.daycare.entity.Caregiver;

public class ViewDetailsDTO {

    private Long id;
    private Long jobId;
    private String jobTitle;
    private Long parentId;
    private String parentName;
    private Long caregiverId;
    private String caregiverName;
    private Caregiver caregiver;

    public ViewDetailsDTO() {
    }

    public ViewDetailsDTO(Long id, Long jobId, String jobTitle, Long parentId, String parentName,
                          Long caregiverId, String caregiverName, Caregiver caregiver) {
        this.id = id;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.parentId = parentId;
        this.parentName = parentName;
        this.caregiverId = caregiverId;
        this.caregiverName = caregiverName;
        this.caregiver = caregiver;
    }

    // ✅ Overloaded constructor without Caregiver object (for lightweight mapping)
    public ViewDetailsDTO(Long id, Long jobId, String jobTitle, Long parentId, String parentName,
                          Long caregiverId, String caregiverName) {
        this(id, jobId, jobTitle, parentId, parentName, caregiverId, caregiverName, null);
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public Long getParentId() { return parentId; }
    public void setParentId(Long parentId) { this.parentId = parentId; }

    public String getParentName() { return parentName; }
    public void setParentName(String parentName) { this.parentName = parentName; }

    public Long getCaregiverId() { return caregiverId; }
    public void setCaregiverId(Long caregiverId) { this.caregiverId = caregiverId; }

    public String getCaregiverName() { return caregiverName; }
    public void setCaregiverName(String caregiverName) { this.caregiverName = caregiverName; }

    public Caregiver getCaregiver() { return caregiver; }
    public void setCaregiver(Caregiver caregiver) { this.caregiver = caregiver; }
}
