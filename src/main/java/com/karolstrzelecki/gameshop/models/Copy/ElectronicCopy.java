package com.karolstrzelecki.gameshop.models.Copy;


import javax.persistence.Entity;

@Entity
public class ElectronicCopy extends Copy {

    private String licenseCode;

    private DigitalDistributionPlatform digitalDistributionPlatform;

    public ElectronicCopy() {
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public DigitalDistributionPlatform getDigitalDistributionPlatform() {
        return digitalDistributionPlatform;
    }

    public void setDigitalDistributionPlatform(DigitalDistributionPlatform digitalDistributionPlatform) {
        this.digitalDistributionPlatform = digitalDistributionPlatform;
    }
//
//    public Long getElectronicCopyId() {
//        return electronicCopyId;
//    }
//
//    public void setElectronicCopyId(Long electronicCopyId) {
//        this.electronicCopyId = electronicCopyId;
//    }
}
