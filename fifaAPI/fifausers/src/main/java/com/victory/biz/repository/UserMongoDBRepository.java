package com.victory.biz.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.victory.biz.model.DivisionVo;

public interface UserMongoDBRepository extends MongoRepository<DivisionVo, String> {
    public DivisionVo findByDivisionName(String name);
    public DivisionVo findByDivisionId(int id);
}