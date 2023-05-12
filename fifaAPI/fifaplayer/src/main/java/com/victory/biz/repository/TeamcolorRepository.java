package com.victory.biz.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Predicate;
import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.TeamcolorVo;

@Repository
public interface TeamcolorRepository extends MongoRepository<TeamcolorVo, String>, QuerydslPredicateExecutor<TeamcolorVo>{
	public List<TeamcolorVo> findAll(Predicate predicate);

	public Page<TeamcolorVo> findAllDistinctByKey(Predicate predicate, PageRequest pageRequest);

}