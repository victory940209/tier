package com.victory.biz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victory.biz.criteria.PlayerSearchCriteria;
import com.victory.biz.model.PlayerVo;
import com.victory.biz.repository.PlayerMongoDBRepository;
import com.victory.biz.specification.PlayerSpecification;
import com.victory.system.util.HttpUrlConnectUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SpidService {

	@Autowired
	HttpUrlConnectUtil apiCon;

	@Autowired
	static PlayerMongoDBRepository playerMongoDBRepository;

	static PlayerSpecification playerSpecification;

	@SuppressWarnings("unchecked")
	public List<PlayerVo> getplayer() {
		// spid.json

		List<PlayerVo> result = apiCon.apiGet("spposition.json", null, null, List.class);

		log.info("result : " + result);

		return result;
	}


	public static List<PlayerVo> searchPlayer(PlayerSearchCriteria playerSearchCriteria) {

		List<PlayerVo> result = new ArrayList<>();
		try {
			result = playerMongoDBRepository.findAll(playerSpecification.searchPlayer(playerSearchCriteria));

			log.info("List : " + result);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;

	}
}
