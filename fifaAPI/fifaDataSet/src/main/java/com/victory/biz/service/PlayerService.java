package com.victory.biz.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.SpidVo;
import com.victory.biz.model.TeamcolorVo;
import com.victory.system.util.HttpUrlConnectUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlayerService {

	@Autowired
	private MongoDBService mongoDBService;


	public void setPlayerTeamcolor(String id) {
		List<TeamcolorVo> data = mongoDBService.selTeamcolorAll();

		int count = 1;

		for(TeamcolorVo teamcolorVo :data) {

			List<String> listkey = teamcolorVo.getSpid();

			for(String key:listkey){
				PlayerVo playerVo =  mongoDBService.selPlayer(key);

				mongoDBService.savePlayer(playerVo);

			}
			log.info("key : " +  teamcolorVo.getKey());
			log.info("name : " +  teamcolorVo.getName());
			log.info("count : " +  count++);
		}


	}



}
