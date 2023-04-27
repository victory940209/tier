package com.victory.biz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.BooleanBuilder;
import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.QPlayerVo;
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
	public PlayerVo selPlayer(String key) {

		return playerMongoDBRepository.findById(key).get();
	}

	public void insertPlayer(PlayerVo playerVo) {

		playerMongoDBRepository.insert(playerVo);

	}

	public void insertTeamcolor(TeamcolorVo teamcolorVo) {

		teamcolorMongoDBRepository.insert(teamcolorVo);

	}
	public List<TeamcolorVo> selTeamcolorPlayer(Predicate predicate) {

		List<TeamcolorVo> playerList = teamcolorMongoDBRepository.findAll(predicate);
		log.info("List<TeamcolorVo> : " + playerList.size());
		return playerList;
	}


	public List<TeamcolorVo> selTeamcolorAll() {

		List<TeamcolorVo> teamcolorList = teamcolorMongoDBRepository.findAll();
		log.info("spidList : " + teamcolorList.size());
		return teamcolorList;
	}

	public void savePlayer(PlayerVo playerVo) {
		QPlayerVo qPlayerVo = QPlayerVo.playerVo;

		BooleanExpression condition = qPlayerVo.id.eq(playerVo.getId());

		Optional<PlayerVo> updateOptional = playerMongoDBRepository.findOne(condition);

		PlayerVo playerVoToUpdate = updateOptional.get();

		List<TeamcolorVo> list = playerVoToUpdate.getTeamColor();
		if(playerVo.getTeamColor() != null) {

			list.addAll(playerVo.getTeamColor());
		}

		playerVoToUpdate.setTeamColor(list);

		playerMongoDBRepository.save(playerVoToUpdate);

	}

	public void selTeamcolorAll(TeamcolorVo teamcolorVo) {

		TeamcolorVo optionalTeamcolorVo = teamcolorMongoDBRepository.findByKey(teamcolorVo.getKey());

		optionalTeamcolorVo.setSpid(teamcolorVo.getSpid());
		optionalTeamcolorVo.setPersonnel(teamcolorVo.getPersonnel());

		teamcolorMongoDBRepository.save(optionalTeamcolorVo);

	}
}
