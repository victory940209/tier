package com.victory.biz.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.querydsl.core.types.Predicate;
import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.TeamcolorVo;
import com.victory.biz.model.TraitVo;

public interface TraitMongoDBRepository  extends MongoRepository<TraitVo, String>, QuerydslPredicateExecutor<TraitVo>{
	public List<TraitVo> findAll(Predicate predicate);
    public List<TraitVo> findAll();


}