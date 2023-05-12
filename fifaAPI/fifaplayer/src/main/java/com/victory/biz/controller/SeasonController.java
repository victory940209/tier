package com.victory.biz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victory.biz.model.SeasonVo;
import com.victory.biz.service.SeasonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SeasonController {

	@Autowired
	SeasonService seasonService;

	@PostMapping("/getSeason")
	public List<SeasonVo> getSeason(){
		return seasonService.getSeason();
	}
}
