package com.victory.biz.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.victory.biz.model.ResultVo;
import com.victory.biz.model.TestVo;
import com.victory.biz.service.CrawlingService;
import com.victory.biz.service.SpidService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DataSetController {

	@Autowired
	CrawlingService crawlingService;


	@PostMapping(value = "/getPlayerCrawling")
	public ResultVo getCrawling(String url){

		crawlingService.getPlayerCrawling(url);

		return ResultVo.builder().result("tete").resultMsg("성공").build();
	}

	@PostMapping(value = "/getTeamcolorCrawling")
	public ResultVo getTeamcolorCrawling(String url){

		crawlingService.setTeamcolorCrawling(url);

		return ResultVo.builder().result("tete").resultMsg("성공").build();
	}
	
}
