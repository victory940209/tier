package com.victory.biz.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.SpidVo;

public interface PlayerMongoDBRepository  extends MongoRepository<PlayerVo, String> {
    public PlayerVo findByName(String name);
    public PlayerVo findByNameContaining(String name);

    public List<PlayerVo> findAll();

}