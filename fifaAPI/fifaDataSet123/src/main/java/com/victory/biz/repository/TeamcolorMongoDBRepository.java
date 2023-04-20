package com.victory.biz.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.querydsl.core.types.Predicate;
import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.TeamcolorVo;

public interface TeamcolorMongoDBRepository  extends MongoRepository<TeamcolorVo, String>, QuerydslPredicateExecutor<TeamcolorVo>{
	public List<TeamcolorVo> findAll(Predicate predicate);
    public TeamcolorVo findByKey(String Key);
    public List<TeamcolorVo> findAll();


}