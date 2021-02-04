package com.karolstrzelecki.gameshop.models.Copy;

import javax.persistence.Entity;

@Entity
public class CaseCopy extends Copy{


    private Conditions conditions;

    public CaseCopy() {
    }

    public Conditions getConditions() {
        return conditions;
    }

    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }


//
//    public Long getCaseCopyId() {
//        return caseCopyId;
//    }
//
//    public void setCaseCopyId(Long caseCopyId) {
//        this.caseCopyId = caseCopyId;
//    }
}
