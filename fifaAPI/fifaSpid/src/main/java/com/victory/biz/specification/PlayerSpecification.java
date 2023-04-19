package com.victory.biz.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victory.biz.criteria.PlayerSearchCriteria;
import com.victory.biz.model.PlayerVo;

public class PlayerSpecification {

	public Specification<PlayerVo> searchPlayer(PlayerSearchCriteria searchKey) {
		return ((root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			ObjectMapper objectMapper = new ObjectMapper();
			@SuppressWarnings("unchecked")
			Map<String, Object> searchMap = objectMapper.convertValue(searchKey, Map.class);

			for (String key : searchMap.keySet()) {
				predicates.add(criteriaBuilder.equal(root.get(key), searchMap.get(key)));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		});
	}
}
