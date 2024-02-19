package com.cvsDemo.demo.Entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "CvsDemo")
public class DemoEntity {
    public String getDrug1() {
        return drug1;
    }

    public void setDrug1(String drug1) {
        this.drug1 = drug1;
    }

    public String getDrug2() {
        return Drug2;
    }

    public void setDrug2(String drug2) {
        Drug2 = drug2;
    }

    public String getDrug1SideEffects() {
        return Drug1SideEffects;
    }

    public void setDrug1SideEffects(String drug1SideEffects) {
        Drug1SideEffects = drug1SideEffects;
    }

    public String getDrug2SideEffects() {
        return Drug2SideEffects;
    }

    public void setDrug2SideEffects(String drug2SideEffects) {
        Drug2SideEffects = drug2SideEffects;
    }

    public String getDrug1UseCase() {
        return Drug1UseCase;
    }

    public void setDrug1UseCase(String drug1UseCase) {
        Drug1UseCase = drug1UseCase;
    }

    public String getDrug2UseCase() {
        return Drug2UseCase;
    }

    public void setDrug2UseCase(String drug2UseCase) {
        Drug2UseCase = drug2UseCase;
    }

    public String getCompatibility() {
        return Compatibility;
    }

    public void setCompatibility(String compatibility) {
        Compatibility = compatibility;
    }

    @DynamoDBHashKey(attributeName = "drug1")
    private String drug1;
    @DynamoDBAttribute(attributeName = "drug2")
    private String Drug2;
    @DynamoDBAttribute(attributeName = "drug1SideEffects")
    private String Drug1SideEffects;
    @DynamoDBAttribute(attributeName = "drug2SideEffects")
    private String Drug2SideEffects;
    @DynamoDBAttribute(attributeName = "drug1UseCase")
    private String Drug1UseCase;
    @DynamoDBAttribute(attributeName = "drug2UseCase")
    private String Drug2UseCase;
    @DynamoDBAttribute(attributeName = "compatibility")
    private String Compatibility;


}
