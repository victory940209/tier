package com.victory.biz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victory.biz.model.TraitVo;
import com.victory.biz.service.TraitService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TraitController {

	@Autowired
	TraitService TraitService;

	@PostMapping("/getTrait")
	public List<TraitVo> getTrait(){
		return TraitService.getTrait();
	}
}
