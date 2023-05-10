package com.victory.biz.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.ResultVo;
import com.victory.biz.service.CrawlingService;
import com.victory.biz.service.PlayerService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DataSetController {

	@Autowired
	CrawlingService crawlingService;

	@Autowired
	PlayerService playerService;


	@PostMapping(value = "/test")
	public ResultVo test(@RequestBody Map<String, String> param){

		crawlingService.test(param.get("test"));

		return ResultVo.builder().result("tete").resultMsg("성공").build();
	}


	@PostMapping(value = "/setPlayerCrawling")
	@Operation(summary = "player insert", description = "신규선수 추가시 teamcolor 먼저 insert후 player insert")
	public ResultVo setPlayerCrawling(String url){

		crawlingService.setPlayerCrawling(url);

		return ResultVo.builder().result("tete").resultMsg("성공").build();
	}

	@PostMapping(value = "/setTeamcolorCrawling")
	// -- swagger 설정 -- swagger-ui에서 나오는 설명.
	@Operation(summary = "teamcolor insert", description = "팀컬러 typeCd {관계 = 32, 스페셜 = 13, 강화 = 21, 국가 = 12, 클럽 = 11} type {관계 = relation, 스페셜 = special, 강화 = grade,국가 = nation, 클럽 = club}")
	public ResultVo setTeamcolorCrawling(String typeCd, String type){

		crawlingService.setTeamcolorCrawling(typeCd, type);

		return ResultVo.builder().result("tete").resultMsg("성공").build();
	}

	@PostMapping(value = "/setPlayerTeamcolorCrawling")
	// -- swagger 설정 -- swagger-ui에서 나오는 설명.
	@Operation(summary = "Player teamcolor insert", description = "신규 선수없이 팀컬러만 추가되어 추가 할 경우 teamcolor inert 후에 돌려야함")
	public ResultVo setTeamcolorPlayerCrawling(String typeCd, String type){

		playerService.setPlayerTeamcolor(typeCd);

		return ResultVo.builder().result("tete").resultMsg("성공").build();
	}


}
