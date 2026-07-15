package com.careconnect.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orphanages")
public class Orphanage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String contactInfo;

    @Column(columnDefinition = "LONGTEXT")
    private String imageUrl;

    private Integer childCount;

    @Column(columnDefinition = "TEXT")
    private String needs;

    private boolean isApproved = false;

    private String bankAccountName;
    private String bankName;
    private String accountNumber;
    private String ifscCode;
    private String upiId;
    @Column(columnDefinition = "LONGTEXT")
    private String qrCodeUrl;
    
    @Column(columnDefinition = "LONGTEXT")
    private String powerBiUrl;

    public Orphanage() {}

    public Orphanage(Long id, User user, String name, String location, String description, String contactInfo, String imageUrl, Integer childCount, String needs, boolean isApproved) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.location = location;
        this.description = description;
        this.contactInfo = contactInfo;
        this.imageUrl = imageUrl;
        this.childCount = childCount;
        this.needs = needs;
        this.isApproved = isApproved;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Integer getChildCount() { return childCount; }
    public void setChildCount(Integer childCount) { this.childCount = childCount; }

    public String getNeeds() { return needs; }
    public void setNeeds(String needs) { this.needs = needs; }

    public boolean isApproved() { return isApproved; }
    public void setApproved(boolean approved) { isApproved = approved; }

    public String getBankAccountName() { return bankAccountName; }
    public void setBankAccountName(String bankAccountName) { this.bankAccountName = bankAccountName; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getIfscCode() { return ifscCode; }
    public void setIfscCode(String ifscCode) { this.ifscCode = ifscCode; }

    public String getUpiId() { return upiId; }
    public void setUpiId(String upiId) { this.upiId = upiId; }

    public String getQrCodeUrl() { return qrCodeUrl; }
    public void setQrCodeUrl(String qrCodeUrl) { this.qrCodeUrl = qrCodeUrl; }
    
    public String getPowerBiUrl() { return powerBiUrl; }
    public void setPowerBiUrl(String powerBiUrl) { this.powerBiUrl = powerBiUrl; }
}
