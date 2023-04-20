package com.victory.biz.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.querydsl.core.types.Predicate;
import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.SpidVo;
import com.victory.biz.model.TeamcolorVo;

public interface PlayerMongoDBRepository  extends MongoRepository<PlayerVo, String> ,QuerydslPredicateExecutor<PlayerVo>{
	public List<PlayerVo> findAll(Predicate predicate);
    public PlayerVo findByName(String name);
    public List<PlayerVo> findAll();

}