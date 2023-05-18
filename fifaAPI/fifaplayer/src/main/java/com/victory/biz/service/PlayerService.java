package com.victory.biz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.victory.biz.model.SearchPlayerVo;
import com.victory.biz.repository.PlayerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerMongoDBRepository;

	public Map<String, Object> searchPlayer(SearchPlayerVo SearchplayerVo) {

		QPlayerVo qplayerVo = QPlayerVo.playerVo;
		log.info("PlayerVo : " + SearchplayerVo);

		BooleanBuilder builder = new BooleanBuilder();

		// 이름
		if (!SearchplayerVo.getName().equals(""))
			builder.and(qplayerVo.name.like("%" + SearchplayerVo.getName() + "%"));
//		 //시즌
//		if (SearchplayerVo.getSeason().size() != 0) {
//			List<String> list = SearchplayerVo.getSeason();
//			for(String data: list) {
//				builder.or(qplayerVo.season.like("%" + data));
//			}
//		}
//
//		// 급여
//		if (SearchplayerVo.getPaySide().length == 2) {
//			BooleanExpression hasMaxpaySide = qplayerVo.paySide.between(SearchplayerVo.getPaySide()[0],SearchplayerVo.getPaySide()[1]);
//			builder.and(hasMaxpaySide);
//		}
//		// ovr
//		if (SearchplayerVo.getOvr().length == 2) {
//			BooleanExpression hasMaxOvr = qplayerVo.ovr.between(SearchplayerVo.getOvr()[0], SearchplayerVo.getOvr()[1]);
//			builder.and(hasMaxOvr);
//		}
//		// 포지션
//		if (!SearchplayerVo.getMainPosition().isEmpty() && !SearchplayerVo.getMainPosition().get(0).equals(""))
//			builder.and(qplayerVo.mainPosition.any().in(SearchplayerVo.getMainPosition()));
//		// 키
//		if (SearchplayerVo.getHeight().length == 2) {
//			BooleanExpression hasMaxHeight = qplayerVo.height.between(SearchplayerVo.getHeight()[0],SearchplayerVo.getHeight()[1]);
//			builder.and(hasMaxHeight);
//		}
//		// 개인기
//		if (!SearchplayerVo.getSkill().equals(""))
//			builder.and(qplayerVo.season.eq(SearchplayerVo.getSkill()));
//		// 왼발
//		if (Integer.valueOf(SearchplayerVo.getLFoot()) != null && SearchplayerVo.getLFoot() != 0)
//			builder.and(qplayerVo.lFoot.eq(SearchplayerVo.getLFoot()));
//		// 오른발
//		if (Integer.valueOf(SearchplayerVo.getRFoot()) != null && SearchplayerVo.getRFoot() != 0)
//			builder.and(qplayerVo.rFoot.eq(SearchplayerVo.getRFoot()));
//		// 국가
//		if (!SearchplayerVo.getNation().equals(""))
//			builder.and(qplayerVo.nation.eq(SearchplayerVo.getNation()));
//		// 특성
//		if (!SearchplayerVo.getTraits().isEmpty() && !SearchplayerVo.getTraits().get(0).equals(""))
//			builder.and(qplayerVo.traits.any().in(SearchplayerVo.getTraits()));
//		// 속력
//		if (SearchplayerVo.getDetailStat1().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat1.between(SearchplayerVo.getDetailStat1()[0],SearchplayerVo.getDetailStat1()[1]);
//			builder.and(detailStat);
//		}
//		// 가속력
//		if (SearchplayerVo.getDetailStat2().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat2.between(SearchplayerVo.getDetailStat2()[0],SearchplayerVo.getDetailStat2()[1]);
//			builder.and(detailStat);
//		}
//		// 골 결정력
//		if (SearchplayerVo.getDetailStat3().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat3.between(SearchplayerVo.getDetailStat3()[0],SearchplayerVo.getDetailStat3()[1]);
//			builder.and(detailStat);
//		}
//		// 슛 파워
//		if (SearchplayerVo.getDetailStat4().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat4.between(SearchplayerVo.getDetailStat4()[0],SearchplayerVo.getDetailStat4()[1]);
//			builder.and(detailStat);
//		}
//		// 중거리 슛
//		if (SearchplayerVo.getDetailStat5().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat5.between(SearchplayerVo.getDetailStat5()[0],SearchplayerVo.getDetailStat5()[1]);
//			builder.and(detailStat);
//		}
//		// 위치 선정
//		if (SearchplayerVo.getDetailStat6().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat6.between(SearchplayerVo.getDetailStat6()[0],SearchplayerVo.getDetailStat6()[1]);
//			builder.and(detailStat);
//		}
//		// 발리슛
//		if (SearchplayerVo.getDetailStat7().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat7.between(SearchplayerVo.getDetailStat7()[0],SearchplayerVo.getDetailStat7()[1]);
//			builder.and(detailStat);
//		}
//		// 페널티 킥
//		if (SearchplayerVo.getDetailStat8().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat8.between(SearchplayerVo.getDetailStat8()[0],SearchplayerVo.getDetailStat8()[1]);
//			builder.and(detailStat);
//		}
//		// 짧은 패스
//		if (SearchplayerVo.getDetailStat9().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat9.between(SearchplayerVo.getDetailStat9()[0],SearchplayerVo.getDetailStat9()[1]);
//			builder.and(detailStat);
//		}
//		// 시야
//		if (SearchplayerVo.getDetailStat10().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat10.between(SearchplayerVo.getDetailStat10()[0],SearchplayerVo.getDetailStat10()[1]);
//			builder.and(detailStat);
//		}
//		// 크로스
//		if (SearchplayerVo.getDetailStat11().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat11.between(SearchplayerVo.getDetailStat11()[0],SearchplayerVo.getDetailStat11()[1]);
//			builder.and(detailStat);
//		}
//		// 긴 패스
//		if (SearchplayerVo.getDetailStat12().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat12.between(SearchplayerVo.getDetailStat12()[0],SearchplayerVo.getDetailStat12()[1]);
//			builder.and(detailStat);
//		}
//		// 프리킥
//		if (SearchplayerVo.getDetailStat13().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat13.between(SearchplayerVo.getDetailStat13()[0],SearchplayerVo.getDetailStat13()[1]);
//			builder.and(detailStat);
//		}
//		// 커브
//		if (SearchplayerVo.getDetailStat14().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat14.between(SearchplayerVo.getDetailStat14()[0],SearchplayerVo.getDetailStat14()[1]);
//			builder.and(detailStat);
//		}
//		// 드리블
//		if (SearchplayerVo.getDetailStat15().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat15.between(SearchplayerVo.getDetailStat15()[0],SearchplayerVo.getDetailStat15()[1]);
//			builder.and(detailStat);
//		}
//		// 볼 컨트롤
//		if (SearchplayerVo.getDetailStat16().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat16.between(SearchplayerVo.getDetailStat16()[0],SearchplayerVo.getDetailStat16()[1]);
//			builder.and(detailStat);
//		}
//		// 민첩성
//		if (SearchplayerVo.getDetailStat17().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat17.between(SearchplayerVo.getDetailStat17()[0],SearchplayerVo.getDetailStat17()[1]);
//			builder.and(detailStat);
//		}
//
//		// 밸런스
//		if (SearchplayerVo.getDetailStat18().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat18.between(SearchplayerVo.getDetailStat18()[0],SearchplayerVo.getDetailStat18()[1]);
//			builder.and(detailStat);
//		}
//
//		// 반응 속도
//		if (SearchplayerVo.getDetailStat19().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat19.between(SearchplayerVo.getDetailStat19()[0],SearchplayerVo.getDetailStat19()[1]);
//			builder.and(detailStat);
//		}
//		// 대인 수비
//		if (SearchplayerVo.getDetailStat20().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat20.between(SearchplayerVo.getDetailStat20()[0],SearchplayerVo.getDetailStat20()[1]);
//			builder.and(detailStat);
//		}
//
//		// 태클
//		if (SearchplayerVo.getDetailStat21().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat21.between(SearchplayerVo.getDetailStat21()[0],SearchplayerVo.getDetailStat21()[1]);
//			builder.and(detailStat);
//		}
//		// 가로채기
//
//		if (SearchplayerVo.getDetailStat22().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat22.between(SearchplayerVo.getDetailStat22()[0],SearchplayerVo.getDetailStat22()[1]);
//			builder.and(detailStat);
//		}
//
//		// 헤더
//		if (SearchplayerVo.getDetailStat23().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat23.between(SearchplayerVo.getDetailStat23()[0],SearchplayerVo.getDetailStat23()[1]);
//			builder.and(detailStat);
//		}
//
//		// 슬라이딩 태클
//		if (SearchplayerVo.getDetailStat24().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat24.between(SearchplayerVo.getDetailStat24()[0],SearchplayerVo.getDetailStat24()[1]);
//			builder.and(detailStat);
//		}
//
//		// 몸싸움
//		if (SearchplayerVo.getDetailStat25().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat25.between(SearchplayerVo.getDetailStat25()[0],SearchplayerVo.getDetailStat25()[1]);
//			builder.and(detailStat);
//		}
//		// 스태미너
//		if (SearchplayerVo.getDetailStat26().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat26.between(SearchplayerVo.getDetailStat26()[0],SearchplayerVo.getDetailStat26()[1]);
//			builder.and(detailStat);
//		}
//		// 적극성
//		if (SearchplayerVo.getDetailStat27().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat27.between(SearchplayerVo.getDetailStat27()[0],SearchplayerVo.getDetailStat27()[1]);
//			builder.and(detailStat);
//		}
//		// 점프
//		if (SearchplayerVo.getDetailStat28().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat28.between(SearchplayerVo.getDetailStat28()[0],SearchplayerVo.getDetailStat28()[1]);
//			builder.and(detailStat);
//		}
//
//		// 침착성
//		if (SearchplayerVo.getDetailStat29().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat29.between(SearchplayerVo.getDetailStat29()[0],SearchplayerVo.getDetailStat29()[1]);
//			builder.and(detailStat);
//		}
//
//		// GK 다이빙
//		if (SearchplayerVo.getDetailStat30().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat30.between(SearchplayerVo.getDetailStat30()[0],SearchplayerVo.getDetailStat30()[1]);
//			builder.and(detailStat);
//		}
//
//		// GK 핸들링
//		if (SearchplayerVo.getDetailStat31().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat31.between(SearchplayerVo.getDetailStat31()[0],SearchplayerVo.getDetailStat31()[1]);
//			builder.and(detailStat);
//		}
//
//		// GK 킥
//		if (SearchplayerVo.getDetailStat32().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat32.between(SearchplayerVo.getDetailStat32()[0],SearchplayerVo.getDetailStat32()[1]);
//			builder.and(detailStat);
//		}
//		// GK 반응속도
//		if (SearchplayerVo.getDetailStat33().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat33.between(SearchplayerVo.getDetailStat33()[0],SearchplayerVo.getDetailStat33()[1]);
//			builder.and(detailStat);
//		}
//
//		// GK 위치 선정
//		if (SearchplayerVo.getDetailStat34().length == 2) {
//			BooleanExpression detailStat = qplayerVo.detailStat34.between(SearchplayerVo.getDetailStat34()[0],SearchplayerVo.getDetailStat34()[1]);
//			builder.and(detailStat);
//		}

//		// 클럽이름
//		if (!SearchplayerVo.getClubName().isEmpty() && !SearchplayerVo.getClubName().get(0).equals(""))
//			builder.and(qplayerVo.clubName.any().in(SearchplayerVo.getClubName()));

		Sort sort = Sort.by("ovr").descending();
		Predicate predicate = builder.getValue();
		PageRequest pageRequest = PageRequest.of(SearchplayerVo.getPage(), SearchplayerVo.getRowsPerPage(), sort);

		Page<PlayerVo> page = playerMongoDBRepository.findAll(predicate, pageRequest);
		List<PlayerVo> result = page.getContent();




		Gson gson = new Gson();
		String listJson = gson.toJson(result, List.class).toString();
		log.info("########## result : " + listJson);
		log.info("########## size : " + result.size());

		Map<String, Object> res = new HashMap<String, Object>();

		res.put("list", result);
		res.put("allItemCount", result.size());

		return res;

	}
}
