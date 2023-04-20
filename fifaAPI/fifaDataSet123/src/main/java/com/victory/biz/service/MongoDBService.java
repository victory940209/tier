package com.victory.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.SpidVo;
import com.victory.biz.model.TeamcolorVo;
import com.victory.biz.repository.PlayerMongoDBRepository;
import com.victory.biz.repository.SpidMongoDBRepository;
import com.victory.biz.repository.TeamcolorMongoDBRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MongoDBService {

	@Autowired
	SpidMongoDBRepository spidMongoDBRepository;

	@Autowired
	PlayerMongoDBRepository playerMongoDBRepository;

	@Autowired
	TeamcolorMongoDBRepository teamcolorMongoDBRepository;

	public List<SpidVo> selectSpidAll() {

		List<SpidVo> spidList = spidMongoDBRepository.findAll();

		log.info("spidList : " + spidList.size());

		return spidList;
	}

	public void insertPlayer(PlayerVo playerVo) {

		playerMongoDBRepository.insert(playerVo);

	}

	public void insertTeamcolor(TeamcolorVo teamcolorVo) {

		teamcolorMongoDBRepository.insert(teamcolorVo);

	}

	public void insertTeamcolor2(TeamcolorVo teamcolorVo) {

		TeamcolorVo optionalTeamcolorVo = teamcolorMongoDBRepository.findByKey(teamcolorVo.getKey());

		optionalTeamcolorVo.setSpid(teamcolorVo.getSpid());
		optionalTeamcolorVo.setPersonnel(teamcolorVo.getPersonnel());

		teamcolorMongoDBRepository.save(optionalTeamcolorVo);


	}
}
