package com.victory.biz.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.SpidVo;
import com.victory.biz.model.TeamcolorVo;
import com.victory.biz.model.TraitVo;
import com.victory.system.util.HttpUrlConnectUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CrawolingTService {

	@Autowired
	private MongoDBService mongoDBService;

	public void setTraitCrawling() {

		try {

			Document doc = Jsoup
					.connect("https://fifaonline4.nexon.com/datacenter").get();

			Element fdata = doc.select("div.search_po_ab").select(".ability_list").get(0);
			Elements data = fdata.select(".selector_item");
			int i=0;
			for (Element param : data) {
				if(i++ != 0) {
					TraitVo traitVo = new TraitVo();
					traitVo.setName(param.text());
					log.info("Element : " + param.text());

					mongoDBService.insertTrait(traitVo);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
