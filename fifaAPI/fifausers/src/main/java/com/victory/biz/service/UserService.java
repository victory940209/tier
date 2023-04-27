package com.victory.biz.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.victory.biz.model.MarketVo;
import com.victory.biz.model.MatchVo;
import com.victory.biz.model.MaxdivisionVo;
import com.victory.biz.model.PriceVo;
import com.victory.biz.model.SearchInfoVo;
import com.victory.biz.model.UserVo;
import com.victory.system.util.HttpUrlConnectUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	HttpUrlConnectUtil apiCon;


	public UserVo getUserToNickNm(String nickNm) throws Exception {

		UserVo resUserVo = apiCon.apiGet("users?nickname=" + nickNm, nickNm,UserVo.class);

		log.info("resUserVo : " + resUserVo);

		return resUserVo;
	}

	@SuppressWarnings("unchecked")
	public List<MatchVo> getUserMatches(String accessid, SearchInfoVo searchInfoVo) throws Exception {

		List<MatchVo> resMatchVo = new ArrayList<>();


		List<String> resMatchList = apiCon.apiGet("users/" + accessid + "/matches?matchType=" + searchInfoVo.getMatchtype() + "&offset=" +
																searchInfoVo.getOffset() + "&limit=" + searchInfoVo.getLimit(), accessid, List.class);

		if(!resMatchList.isEmpty()) {
			for(String resMatch : resMatchList) {
				log.info("#### resMatch : " + resMatch);
				MatchVo matchVo = apiCon.apiGet("matches/" + resMatch , resMatch, MatchVo.class);
				log.info("#### matchVo : " + matchVo);
				resMatchVo.add(matchVo);

			}
		}

		return resMatchVo;
	}


	public List<MaxdivisionVo> getUserMaxdivision(String accessid) throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		List<MaxdivisionVo> resMaxdivisionVo = mapper.convertValue(apiCon.apiGet("users/" + accessid + "/maxdivision", accessid, List.class),new TypeReference<List<MaxdivisionVo>>(){});

		log.info("resMaxdivisionVo : " + resMaxdivisionVo);

		return resMaxdivisionVo;
	}

	public List<MarketVo> getUserMarket(String accessid, SearchInfoVo searchInfoVo) throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		List<MarketVo> res = mapper.convertValue(apiCon.apiGet("users/" + accessid + "/markets?tradetype=" + searchInfoVo.getTradetype() + "&offset=" +
				searchInfoVo.getOffset() + "&limit=" +
				searchInfoVo.getLimit()
				, accessid, List.class),new TypeReference<List<MarketVo>>(){});

		for(MarketVo data :res) {
			data.setPriceVo(getprice(data.getSpid(), data.getGrade()));
		}

		return res;
	}

	public PriceVo getprice(String spid, String grade) {
		Map<String, String> price = new HashMap<>();
		price.put("spid", spid);
		price.put("n1strong", grade);

		PriceVo priceVo = new PriceVo();


		Document doc;
		try {
			doc = Jsoup.connect("https://fifaonline4.nexon.com/datacenter/PlayerPriceGraph").data(price).post();

			priceVo.setToValue(doc.select("div.add_info").select("Strong").text().replace(",", "").replace("BP", "").replace(" ", ""));

			Elements value = doc.select("div.price_tab").select("li");

			priceVo.setMaxValue(value.get(0).select("strong").text().replace(",", "").replace("BP", "").replace(" ", ""));
			priceVo.setMinValue(value.get(1).select("strong").text().replace(",", "").replace("BP", "").replace(" ", ""));

			Elements scripts = doc.select("script");
			String a = null;

			for (Element element : scripts) {
				if (element.data().contains("var json1")) {
					Pattern pattern = Pattern.compile(".*var json1 = ([^;]*);");
					Matcher matcher = pattern.matcher(element.data());
					if (matcher.find()) {
						a = matcher.group(1);
						break;
					} else {
						System.err.println("No match found!");
					}
					break;
				}
			}
			String strData = (a.substring(0, a.indexOf("}")+1));

			Gson gson = new Gson();
			PriceVo Json = gson.fromJson(strData, PriceVo.class);
			priceVo.setTime(Json.getTime());
			priceVo.setValue(Json.getValue());

		} catch (IOException e) {

			e.printStackTrace();
		}




		return priceVo;
	}

}
