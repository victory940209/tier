package com.victory.biz.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.victory.biz.model.SpidVo;

public interface SpidMongoDBRepository extends MongoRepository<SpidVo, String>, JpaSpecificationExecutor<SpidVo> {
    public SpidVo findByName(String name);
    public List<SpidVo> findAll();


}