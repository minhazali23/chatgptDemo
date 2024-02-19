package com.cvsDemo.demo.DTO;


import java.io.Serializable;

public class DemoDTO implements Serializable {

    public String getDrug1() {
        return drug1;
    }

    public void setDrug1(String drug1) {
        this.drug1 = drug1;
    }

    public String getDrug2() {
        return drug2;
    }

    public void setDrug2(String drug2) {
        this.drug2 = drug2;
    }

    public String getDrug1SideEffects() {
        return drug1SideEffects;
    }

    public void setDrug1SideEffects(String drug1SideEffects) {
        this.drug1SideEffects = drug1SideEffects;
    }

    public String getDrug2SideEffects() {
        return drug2SideEffects;
    }

    public void setDrug2SideEffects(String drug2SideEffects) {
        this.drug2SideEffects = drug2SideEffects;
    }

    public String getDrug1UseCase() {
        return drug1UseCase;
    }

    public void setDrug1UseCase(String drug1UseCase) {
        this.drug1UseCase = drug1UseCase;
    }

    public String getDrug2UseCase() {
        return drug2UseCase;
    }

    public void setDrug2UseCase(String drug2UseCase) {
        this.drug2UseCase = drug2UseCase;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

    private String drug1;
    private String drug2;
    private String drug1SideEffects;
    private String drug2SideEffects;
    private String drug1UseCase;
    private String drug2UseCase;
    private String compatibility;

}
