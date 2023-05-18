package com.victory.biz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.ResultVo;
import com.victory.biz.model.SearchPlayerVo;
import com.victory.biz.model.TeamcolorVo;
import com.victory.biz.service.PlayerService;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class playerController {

	@Autowired
	PlayerService playerService;

	@PostMapping(value = "/getPlayer")
	public Map<String, Object> getPlayer(@RequestBody SearchPlayerVo searchPlayerVo){

		return playerService.searchPlayer(searchPlayerVo);
	}

}
