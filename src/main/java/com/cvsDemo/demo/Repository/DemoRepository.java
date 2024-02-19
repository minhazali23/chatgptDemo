package com.cvsDemo.demo.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.cvsDemo.demo.DTO.DemoDTO;
import com.cvsDemo.demo.Entities.DemoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;

@Repository
public class DemoRepository {


    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    //CREATE
    public DemoDTO saveOne(DemoDTO saveDTO){
        DemoEntity saveEntity = new DemoEntity();
        saveEntity.setDrug1(saveDTO.getDrug1());
        saveEntity.setDrug2(saveDTO.getDrug2());
        saveEntity.setDrug1UseCase(saveDTO.getDrug1UseCase());
        saveEntity.setDrug2UseCase(saveDTO.getDrug2UseCase());
        saveEntity.setDrug1SideEffects(saveDTO.getDrug1SideEffects());
        saveEntity.setDrug2SideEffects(saveDTO.getDrug2SideEffects());
        saveEntity.setCompatibility(saveDTO.getCompatibility());

        dynamoDBMapper.save(saveEntity);

        return saveDTO;
    }

    //READ
    public DemoDTO getOne(String name){
        DemoDTO getDTO = new DemoDTO();
        try{
            DemoEntity getEntity = dynamoDBMapper.load(DemoEntity.class, name);
            getDTO.setDrug1(getEntity.getDrug1());
            getDTO.setDrug2(getEntity.getDrug2());
            getDTO.setDrug1UseCase(getEntity.getDrug1UseCase());
            getDTO.setDrug2UseCase(getEntity.getDrug2UseCase());
            getDTO.setDrug1SideEffects(getEntity.getDrug1SideEffects());
            getDTO.setDrug2SideEffects(getEntity.getDrug2SideEffects());
            getDTO.setCompatibility(getEntity.getCompatibility());

        }catch (NullPointerException ex){
            System.out.println(ex);
        }

        return getDTO;
    }

    //DELETE
    public void deleteOne(String name){
        DemoEntity deleteThis = new DemoEntity();
        deleteThis.setDrug1(name);
        dynamoDBMapper.delete(deleteThis);
    }

}
