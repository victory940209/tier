package com.victory.biz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victory.biz.model.SearchTeamcolorVo;
import com.victory.biz.model.TeamcolorVo;
import com.victory.biz.service.TeamcolorService;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TeamcolorController {

	@Autowired
	TeamcolorService teamcolorService;

	@PostMapping("/getTeamcolor")
	public List<TeamcolorVo> getTeamcolor(@RequestBody SearchTeamcolorVo searchTeamcolorVo){

		log.info("searchTeamcolorVo : " + searchTeamcolorVo);

		return teamcolorService.getTeamcolor(searchTeamcolorVo);

	}
}
