package com.victory.biz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.querydsl.core.types.Predicate;
import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.SeasonVo;

public interface SeasonMongoDBRepository  extends MongoRepository<SeasonVo, String>, QuerydslPredicateExecutor<SeasonVo>{
	public List<SeasonVo> findAll(Predicate predicate);
    public List<SeasonVo> findAll();
	public SeasonVo findBySeasonId(int id);

}