package com.victory.biz.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.victory.biz.model.SpidVo;

public interface SpidMongoDBRepository extends MongoRepository<SpidVo, String> {
    public SpidVo findByName(String name);
    public List<SpidVo> findAll();


}