package com.victory.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.QPlayerVo;
import com.victory.biz.model.SearchPlayerVo;
import com.victory.biz.repository.PlayerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerMongoDBRepository;

	@SuppressWarnings("unchecked")
	public List<PlayerVo> searchPlayer(SearchPlayerVo SearchplayerVo) {

		QPlayerVo qplayerVo = QPlayerVo.playerVo;
		log.info("PlayerVo : " + SearchplayerVo);
//		최솟값
//	    BooleanExpression hasMinAge = qUser.age.goe(minAge);
//		builder.and(hasMinAge);
//		최댓값
//	    BooleanExpression hasMaxAge = qUser.age.loe(maxAge);
//	    builder.and(hasMaxAge);

		int pageNumber = 0;
		int pageSize = 30;
		BooleanBuilder builder = new BooleanBuilder();

		//
		if(!SearchplayerVo.getName().equals(""))
			builder.and(qplayerVo.name.like("%"+SearchplayerVo.getName()+"%"));

		if(!SearchplayerVo.getSeason().equals("")) {
			log.info("########## getSeason : " + SearchplayerVo.getSeason());
			builder.and(qplayerVo.season.like("%"+SearchplayerVo.getSeason()+"%"));
		}
		if(Integer.valueOf(SearchplayerVo.getPaySide()) != null && SearchplayerVo.getPaySide() != 0) {
			 BooleanExpression hasMaxpaySide = qplayerVo.paySide.loe(SearchplayerVo.getPaySide());
			 builder.and(hasMaxpaySide);
		}
		//
		if(!SearchplayerVo.getMainPosition().isEmpty())
			for(String data  :SearchplayerVo.getMainPosition())
				builder.and(qplayerVo.mainPosition.contains(data));


//		PathBuilder<PlayerVo> entityPath = new PathBuilder<>(PlayerVo.class, "playerVo");
//		OrderSpecifier<String> orderSpecifier = entityPath.getString("paySide").asc();

		Sort sort = Sort.by("ovr").ascending();

	    Predicate predicate = builder.getValue();

		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

		Page<PlayerVo> page = playerMongoDBRepository.findAll(predicate, pageRequest);
		List<PlayerVo> result = page.getContent();
//		List<PlayerVo> result  = (List<PlayerVo>) playerMongoDBRepository.findAll(predicate, pageRequest);

		Gson gson = new Gson();
		String listJson = gson.toJson(result, List.class).toString();
		log.info("########## result : " + listJson);
		log.info("########## size : " + result.size());


	    return null;

	}
}
