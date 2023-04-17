package com.victory.biz.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.victory.biz.criteria.PlayerSearchCriteria;
import com.victory.biz.model.PlayerVo;

public class PlayerSpecification {

	public static Specification<PlayerVo> searchLecture(PlayerSearchCriteria searchKey){
		return ((root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			for( key : searchKey){
				predicates.add(criteriaBuilder.equal(root.get(key), searchKey.get(key)));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		});
	}
}
