package com.victory.biz.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.QPlayerVo;
import com.victory.biz.model.QTeamcolorVo;
import com.victory.biz.model.SearchTeamcolorVo;
import com.victory.biz.model.TeamcolorVo;
import com.victory.biz.repository.PlayerRepository;
import com.victory.biz.repository.TeamcolorRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TeamcolorService {


	@Autowired
	private TeamcolorRepository teamcolorRepository;


	public List<TeamcolorVo> getTeamcolor(SearchTeamcolorVo searchTeamcolorVo) {

		QTeamcolorVo qTeamcolorVo = QTeamcolorVo.teamcolorVo;
		log.info("searchTeamcolorVo : " + searchTeamcolorVo);

		int pageNumber = 0;
		int pageSize = 10000;
		BooleanBuilder builder = new BooleanBuilder();


		builder.and(qTeamcolorVo.name.like("%" + "" + "%"));

		if(searchTeamcolorVo != null) {
			// 이름
			if (searchTeamcolorVo.getName()!= null && !searchTeamcolorVo.getName().equals(""))
				builder.and(qTeamcolorVo.name.like("%" + searchTeamcolorVo.getName() + "%"));
			// type
			if (searchTeamcolorVo.getType()!= null && !searchTeamcolorVo.getType().equals(""))
				builder.and(qTeamcolorVo.type.like("%" + searchTeamcolorVo.getType() + "%"));
		}


		Sort sort = Sort.by("name").ascending();

		Predicate predicate = builder.getValue();
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);


		Page<TeamcolorVo> page = teamcolorRepository.findAll(predicate, pageRequest);
		List<TeamcolorVo> result = page.getContent();

		Gson gson = new Gson();
		String listJson = gson.toJson(result, List.class).toString();

		if(searchTeamcolorVo.getDistinct() != null && searchTeamcolorVo.getDistinct()) {
			Set<Object> distinctValues = new HashSet<>();
		    List<TeamcolorVo> distinctObjects = new ArrayList<>();

		    for (TeamcolorVo obj : result) {
		        Object value = obj.getKey();
		        if (value != null && distinctValues.add(value)) {
		            distinctObjects.add(obj);
		        }
		    }
		    listJson = gson.toJson(distinctObjects, List.class).toString();
			return distinctObjects;
		}else {

			return result;
		}

	}


}
