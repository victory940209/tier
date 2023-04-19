package com.victory.biz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victory.biz.model.ResultVo;
import com.victory.biz.model.SearchPlayerVo;
import com.victory.biz.service.PlayerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class playerController {

	@Autowired
	PlayerService playerService;

	@PostMapping(value = "/getPlayer")
	public ResultVo getPlayer(SearchPlayerVo searchPlayerVo){

		log.info("playerController : " + playerService.searchPlayer(searchPlayerVo));

		return ResultVo.builder().result("tete").resultMsg("성공").build();
	}
}
