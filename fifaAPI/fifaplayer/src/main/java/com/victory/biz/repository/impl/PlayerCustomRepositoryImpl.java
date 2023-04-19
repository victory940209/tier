package com.victory.biz.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.victory.biz.model.PlayerVo;
import com.victory.biz.repository.PlayerCustomRepository;

public class PlayerCustomRepositoryImpl implements PlayerCustomRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<PlayerVo> findAll() {
		return mongoTemplate.findAll(PlayerVo.class);

	}

	@Override
	public PlayerVo findBysearch(PlayerVo playerVo) {
		Query query = new Query();

//		query.addCriteria(Criteria.where(parse(playerVo.getame)).is(name));


		return mongoTemplate.findOne(query, PlayerVo.class);
	}

}
