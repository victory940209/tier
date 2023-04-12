package com.victory.biz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victory.biz.model.MatchVo;
import com.victory.biz.model.MaxdivisionVo;
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

}
