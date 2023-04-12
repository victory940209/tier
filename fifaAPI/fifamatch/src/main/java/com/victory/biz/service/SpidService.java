package com.victory.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.victory.biz.model.SpidVo;
import com.victory.biz.model.SppositionVo;
import com.victory.system.util.HttpUrlConnectUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SpidService {

	@Autowired
	HttpUrlConnectUtil apiCon;

	@SuppressWarnings("unchecked")
	public List<SppositionVo> getSpposition() {
		//spid.json

		List<SppositionVo> result = apiCon.apiGet("spposition.json", null, null, List.class);

		log.info("result : " + result);

		return result;
	}
}
