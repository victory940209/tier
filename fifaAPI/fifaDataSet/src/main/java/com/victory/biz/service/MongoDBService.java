package com.victory.biz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.QPlayerVo;
import com.victory.biz.model.SeasonVo;
import com.victory.biz.model.SpidVo;
import com.victory.biz.model.TeamcolorVo;
import com.victory.biz.model.TraitVo;
import com.victory.biz.repository.PlayerMongoDBRepository;
import com.victory.biz.repository.SeasonMongoDBRepository;
import com.victory.biz.repository.SpidMongoDBRepository;
import com.victory.biz.repository.TeamcolorMongoDBRepository;
import com.victory.biz.repository.TraitMongoDBRepository;

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

	@Autowired
	TraitMongoDBRepository traitMongoDBRepository;

	@Autowired
	SeasonMongoDBRepository seasonMongoDBRepository;

	//spid 모두 조회
	public List<SpidVo> selectSpidAll() {

		List<SpidVo> spidList = spidMongoDBRepository.findAll();

		log.info("spidList : " + spidList.size());

		return spidList;
	}
	//player key로 조회
	public PlayerVo selPlayer(String key) {

		return playerMongoDBRepository.findById(key).get();
	}
	//player insert
	public void insertPlayer(PlayerVo playerVo) {

		playerMongoDBRepository.insert(playerVo);

	}
	//teamcolor insert
	public void insertTeamcolor(TeamcolorVo teamcolorVo) {

		teamcolorMongoDBRepository.insert(teamcolorVo);

	}
	//teamcolor  player key로 조회
	public List<TeamcolorVo> selTeamcolorPlayer(Predicate predicate) {

		List<TeamcolorVo> playerList = teamcolorMongoDBRepository.findAll(predicate);
		log.info("List<TeamcolorVo> : " + playerList.size());
		return playerList;
	}
	//teamcolor 모두 조회
	public List<TeamcolorVo> selTeamcolorAll() {

		List<TeamcolorVo> teamcolorList = teamcolorMongoDBRepository.findAll();
		log.info("spidList : " + teamcolorList.size());
		return teamcolorList;
	}
	//player save(teamcolor저장용)
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
	//teamolo save(선수 및 인원용)
	public void selTeamcolorAll(TeamcolorVo teamcolorVo) {

		TeamcolorVo optionalTeamcolorVo = teamcolorMongoDBRepository.findByKey(teamcolorVo.getKey());

		optionalTeamcolorVo.setSpid(teamcolorVo.getSpid());
		optionalTeamcolorVo.setPersonnel(teamcolorVo.getPersonnel());

		teamcolorMongoDBRepository.save(optionalTeamcolorVo);

	}
	//trait insert
	public void insertTrait(TraitVo traitVo) {

		traitMongoDBRepository.insert(traitVo);

	}
	//seansonid sel
	public SeasonVo selSeanson(int id) {
		return seasonMongoDBRepository.findBySeasonId(id);
	}

	//player save(Season, image저장용)
	public void savePlayerSeason(PlayerVo playerVo) {
		QPlayerVo qPlayerVo = QPlayerVo.playerVo;

		BooleanExpression condition = qPlayerVo.id.eq(playerVo.getId());

		Optional<PlayerVo> updateOptional = playerMongoDBRepository.findOne(condition);

		PlayerVo playerVoToUpdate = updateOptional.get();

		playerVoToUpdate.setImg(playerVo.getImg());
		playerVoToUpdate.setSeason(playerVo.getSeason());

		playerMongoDBRepository.save(playerVoToUpdate);

	}


}
