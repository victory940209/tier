package com.victory.biz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.ResultVo;
import com.victory.biz.service.CrawlingService;
import com.victory.biz.service.PlayerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DataSetController {

	@Autowired
	CrawlingService crawlingService;

	@Autowired
	PlayerService playerService;


	@PostMapping(value = "/test")
	public ResultVo test(@RequestBody PlayerVo playerVo){

		crawlingService.setTeamColor(playerVo);

		return ResultVo.builder().result("tete").resultMsg("성공").build();
	}


	@PostMapping(value = "/setPlayerCrawling")
	public ResultVo setPlayerCrawling(String url){

		crawlingService.setPlayerCrawling(url);

		return ResultVo.builder().result("tete").resultMsg("성공").build();
	}

	@PostMapping(value = "/setTeamcolorCrawling")
	public ResultVo setTeamcolorCrawling(String url){

		crawlingService.setTeamcolorCrawling(url);

		return ResultVo.builder().result("tete").resultMsg("성공").build();
	}

	@PostMapping(value = "/setPlayerTeamcolor")
	public ResultVo setPlayerTeamcolor(String url){

		playerService.setPlayerTeamcolor(url);

		return ResultVo.builder().result("tete").resultMsg("성공").build();
	}
}
