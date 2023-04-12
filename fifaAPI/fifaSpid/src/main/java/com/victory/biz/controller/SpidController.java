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
import com.victory.biz.service.SpidService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SpidController {

	@Autowired
	SpidService spidService;


	@PostMapping(value = "/getSpposition")
	public ResultVo apiConPostVo(){

		log.info("getSpid : " + spidService.getSpposition());

		return ResultVo.builder().result("tete").resultMsg("성공").build();
	}
}
