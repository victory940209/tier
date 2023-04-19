package com.victory.biz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.victory.biz.model.DivisionVo;
import com.victory.biz.model.MatchVo;
import com.victory.biz.model.MaxdivisionVo;
import com.victory.biz.model.SearchInfoVo;
import com.victory.biz.model.UserVo;
import com.victory.biz.service.MongoDBService;
import com.victory.biz.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private MongoDBService mongoDBService;

	@PostMapping(value = "/test/mongodb")
	public DivisionVo test(@RequestBody DivisionVo params) throws Exception{

		log.info("asdasd : " + params);
		DivisionVo  getDivisionId = mongoDBService.selectDivisionId(params.getDivisionId());
		DivisionVo getDivisionName =  mongoDBService.selectDivisionNm(params.getDivisionName());

		log.info("getDivisionId : " + getDivisionId);
		log.info("getDivisionName : " + getDivisionName);

		return getDivisionName;
	}


	@PostMapping(value = "/getUserToNickNm/{nickNm}")
	public UserVo getUserToNickNm(@PathVariable String nickNm) throws Exception{

		return userService.getUserToNickNm(nickNm);
	}

	@PostMapping(value = "/getUserMatches/{accessid}")
	public List<MatchVo> getUserMatches(@PathVariable String accessid, @RequestBody SearchInfoVo searchInfoVo) throws Exception{

		return userService.getUserMatches(accessid, searchInfoVo);
	}

	@PostMapping(value = "/getUserMaxdivision/{accessid}")
	public List<MaxdivisionVo> getUserMaxdivision(@PathVariable String accessid) throws Exception{

		return userService.getUserMaxdivision(accessid);
	}


}
