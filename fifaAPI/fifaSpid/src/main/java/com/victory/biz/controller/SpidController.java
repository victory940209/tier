package com.victory.biz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victory.biz.criteria.PlayerSearchCriteria;
import com.victory.biz.model.ResultVo;
import com.victory.biz.service.SpidService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SpidController {

	@Autowired
	SpidService spidService;



	@PostMapping(value = "/getPlayer")
	public ResultVo getPlayer(PlayerSearchCriteria playerSearchCriteria){

		spidService.searchPlayer(playerSearchCriteria);

		return ResultVo.builder().result("tete").resultMsg("성공").build();
	}
}
