package com.victory.biz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.victory.biz.model.PlayerVo;

public interface PlayerMongoDBRepository  extends MongoRepository<PlayerVo, String>, JpaSpecificationExecutor<PlayerVo>{
    public PlayerVo findByName(String name);
    public PlayerVo findByNameContaining(String name);
    public List<PlayerVo> findAll();

}