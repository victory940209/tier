package com.victory.biz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.SppositionVo;
import com.victory.biz.repository.SpidMongoDBRepository;
import com.victory.system.util.HttpUrlConnectUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SpidService {

	@Autowired
	HttpUrlConnectUtil apiCon;

	@Autowired
	SpidMongoDBRepository spidMongoDBRepository;


	@SuppressWarnings("unchecked")
	public List<PlayerVo> getplayer() {
		//spid.json


		List<PlayerVo> result = apiCon.apiGet("spposition.json", null, null, List.class);

		log.info("result : " + result);

		return result;
	}

	 public static Specification<Order> memberNameLike(final String memberName) {
	        return new Specification<Order>() {
	            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

	                if (StringUtils.isEmpty(memberName)) return null;

	                Join<Order, Member> m = root.join("member", JoinType.INNER); //회원과 조인
	                return builder.like(m.<String>get("name"), "%" + memberName + "%");
	            }
	        };
	    }
}
