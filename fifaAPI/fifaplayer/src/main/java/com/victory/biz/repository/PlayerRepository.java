package com.victory.biz.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Predicate;
import com.victory.biz.model.PlayerVo;

@Repository
public interface PlayerRepository extends MongoRepository<PlayerVo, String>, QuerydslPredicateExecutor<PlayerVo>{
	public List<PlayerVo> findAll(Predicate predicate);
}