package com.victory.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAUpdateClause;
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

	@SuppressWarnings("unchecked")
	public List<PlayerVo> searchPlayer(SearchPlayerVo SearchplayerVo) {

		QPlayerVo qplayerVo = QPlayerVo.playerVo;
		log.info("PlayerVo : " + SearchplayerVo);

		int pageNumber = 0;
		int pageSize = 30;
		BooleanBuilder builder = new BooleanBuilder();

		// 이름
		if (!SearchplayerVo.getName().equals(""))
			builder.and(qplayerVo.name.like("%" + SearchplayerVo.getName() + "%"));
		// 시즌
		if (!SearchplayerVo.getSeason().equals(""))
			builder.and(qplayerVo.season.like("%" + SearchplayerVo.getSeason() + "%"));

		// 급여
		if (Integer.valueOf(SearchplayerVo.getPaySide()) != null && SearchplayerVo.getPaySide() != 0) {
			BooleanExpression hasMaxpaySide = qplayerVo.paySide.loe(SearchplayerVo.getPaySide());
			builder.and(hasMaxpaySide);
		}
		// ovr
		if (Integer.valueOf(SearchplayerVo.getOvr()) != null && SearchplayerVo.getOvr() != 0) {
			BooleanExpression hasMaxOvr = qplayerVo.ovr.loe(SearchplayerVo.getOvr());
			builder.and(hasMaxOvr);
		}
		// 포지션
		if (!SearchplayerVo.getMainPosition().isEmpty() && !SearchplayerVo.getMainPosition().get(0).equals(""))
			builder.and(qplayerVo.mainPosition.any().in(SearchplayerVo.getMainPosition()));
		// 키
		if (Integer.valueOf(SearchplayerVo.getHeight()) != null && SearchplayerVo.getHeight() != 0) {
			BooleanExpression hasMaxHeight = qplayerVo.height.loe(SearchplayerVo.getHeight());
			builder.and(hasMaxHeight);
		}
		// 개인기
		if (!SearchplayerVo.getSkill().equals(""))
			builder.and(qplayerVo.season.eq(SearchplayerVo.getSkill()));
		// 왼발
		if (Integer.valueOf(SearchplayerVo.getLFoot()) != null && SearchplayerVo.getLFoot() != 0)
			builder.and(qplayerVo.lFoot.eq(SearchplayerVo.getLFoot()));
		// 오른발
		if (Integer.valueOf(SearchplayerVo.getRFoot()) != null && SearchplayerVo.getRFoot() != 0)
			builder.and(qplayerVo.rFoot.eq(SearchplayerVo.getRFoot()));
		// 국가
		if (!SearchplayerVo.getNation().equals(""))
			builder.and(qplayerVo.nation.eq(SearchplayerVo.getNation()));
		// 특성
		if (!SearchplayerVo.getTraits().isEmpty() && !SearchplayerVo.getTraits().get(0).equals(""))
			builder.and(qplayerVo.traits.any().in(SearchplayerVo.getTraits()));
		// 속력
		if (Integer.valueOf(SearchplayerVo.getDetailStat1()) != null && SearchplayerVo.getDetailStat1() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat1.loe(SearchplayerVo.getDetailStat1());
			builder.and(detailStat);
		}
		// 가속력
		if (Integer.valueOf(SearchplayerVo.getDetailStat2()) != null && SearchplayerVo.getDetailStat2() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat2.loe(SearchplayerVo.getDetailStat2());
			builder.and(detailStat);
		}
		// 골 결정력
		if (Integer.valueOf(SearchplayerVo.getDetailStat3()) != null && SearchplayerVo.getDetailStat3() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat3.loe(SearchplayerVo.getDetailStat3());
			builder.and(detailStat);
		}
		// 슛 파워
		if (Integer.valueOf(SearchplayerVo.getDetailStat4()) != null && SearchplayerVo.getDetailStat4() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat4.loe(SearchplayerVo.getDetailStat4());
			builder.and(detailStat);
		}
		// 중거리 슛
		if (Integer.valueOf(SearchplayerVo.getDetailStat5()) != null && SearchplayerVo.getDetailStat5() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat5.loe(SearchplayerVo.getDetailStat5());
			builder.and(detailStat);
		}
		// 위치 선정
		if (Integer.valueOf(SearchplayerVo.getDetailStat6()) != null && SearchplayerVo.getDetailStat6() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat6.loe(SearchplayerVo.getDetailStat6());
			builder.and(detailStat);
		}
		// 발리슛
		if (Integer.valueOf(SearchplayerVo.getDetailStat7()) != null && SearchplayerVo.getDetailStat7() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat7.loe(SearchplayerVo.getDetailStat7());
			builder.and(detailStat);
		}
		// 페널티 킥
		if (Integer.valueOf(SearchplayerVo.getDetailStat8()) != null && SearchplayerVo.getDetailStat8() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat8.loe(SearchplayerVo.getDetailStat8());
			builder.and(detailStat);
		}
		// 짧은 패스
		if (Integer.valueOf(SearchplayerVo.getDetailStat9()) != null && SearchplayerVo.getDetailStat9() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat9.loe(SearchplayerVo.getDetailStat9());
			builder.and(detailStat);
		}
		// 시야
		if (Integer.valueOf(SearchplayerVo.getDetailStat10()) != null && SearchplayerVo.getDetailStat10() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat10.loe(SearchplayerVo.getDetailStat10());
			builder.and(detailStat);
		}
		// 크로스
		if (Integer.valueOf(SearchplayerVo.getDetailStat11()) != null && SearchplayerVo.getDetailStat11() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat11.loe(SearchplayerVo.getDetailStat11());
			builder.and(detailStat);
		}
		// 긴 패스
		if (Integer.valueOf(SearchplayerVo.getDetailStat12()) != null && SearchplayerVo.getDetailStat12() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat12.loe(SearchplayerVo.getDetailStat12());
			builder.and(detailStat);
		}
		// 프리킥
		if (Integer.valueOf(SearchplayerVo.getDetailStat13()) != null && SearchplayerVo.getDetailStat13() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat13.loe(SearchplayerVo.getDetailStat13());
			builder.and(detailStat);
		}
		// 커브
		if (Integer.valueOf(SearchplayerVo.getDetailStat14()) != null && SearchplayerVo.getDetailStat14() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat14.loe(SearchplayerVo.getDetailStat14());
			builder.and(detailStat);
		}
		// 드리블
		if (Integer.valueOf(SearchplayerVo.getDetailStat15()) != null && SearchplayerVo.getDetailStat15() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat15.loe(SearchplayerVo.getDetailStat15());
			builder.and(detailStat);
		}
		// 볼 컨트롤
		if (Integer.valueOf(SearchplayerVo.getDetailStat16()) != null && SearchplayerVo.getDetailStat16() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat16.loe(SearchplayerVo.getDetailStat16());
			builder.and(detailStat);
		}
		// 민첩성
		if (Integer.valueOf(SearchplayerVo.getDetailStat17()) != null && SearchplayerVo.getDetailStat17() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat17.loe(SearchplayerVo.getDetailStat17());
			builder.and(detailStat);
		}

		// 밸런스
		if (Integer.valueOf(SearchplayerVo.getDetailStat18()) != null && SearchplayerVo.getDetailStat18() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat18.loe(SearchplayerVo.getDetailStat18());
			builder.and(detailStat);
		}

		// 반응 속도
		if (Integer.valueOf(SearchplayerVo.getDetailStat19()) != null && SearchplayerVo.getDetailStat19() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat19.loe(SearchplayerVo.getDetailStat19());
			builder.and(detailStat);
		}
		// 대인 수비
		if (Integer.valueOf(SearchplayerVo.getDetailStat20()) != null && SearchplayerVo.getDetailStat20() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat20.loe(SearchplayerVo.getDetailStat20());
			builder.and(detailStat);
		}

		// 태클
		if (Integer.valueOf(SearchplayerVo.getDetailStat21()) != null && SearchplayerVo.getDetailStat21() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat21.loe(SearchplayerVo.getDetailStat21());
			builder.and(detailStat);
		}
		// 가로채기

		if (Integer.valueOf(SearchplayerVo.getDetailStat22()) != null && SearchplayerVo.getDetailStat22() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat22.loe(SearchplayerVo.getDetailStat22());
			builder.and(detailStat);
		}

		// 헤더
		if (Integer.valueOf(SearchplayerVo.getDetailStat23()) != null && SearchplayerVo.getDetailStat23() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat23.loe(SearchplayerVo.getDetailStat23());
			builder.and(detailStat);
		}

		// 슬라이딩 태클
		if (Integer.valueOf(SearchplayerVo.getDetailStat24()) != null && SearchplayerVo.getDetailStat24() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat24.loe(SearchplayerVo.getDetailStat24());
			builder.and(detailStat);
		}

		// 몸싸움
		if (Integer.valueOf(SearchplayerVo.getDetailStat25()) != null && SearchplayerVo.getDetailStat25() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat25.loe(SearchplayerVo.getDetailStat25());
			builder.and(detailStat);
		}
		// 스태미너
		if (Integer.valueOf(SearchplayerVo.getDetailStat26()) != null && SearchplayerVo.getDetailStat26() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat26.loe(SearchplayerVo.getDetailStat26());
			builder.and(detailStat);
		}
		// 적극성
		if (Integer.valueOf(SearchplayerVo.getDetailStat27()) != null && SearchplayerVo.getDetailStat27() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat27.loe(SearchplayerVo.getDetailStat27());
			builder.and(detailStat);
		}
		// 점프
		if (Integer.valueOf(SearchplayerVo.getDetailStat28()) != null && SearchplayerVo.getDetailStat28() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat28.loe(SearchplayerVo.getDetailStat28());
			builder.and(detailStat);
		}

		// 침착성
		if (Integer.valueOf(SearchplayerVo.getDetailStat29()) != null && SearchplayerVo.getDetailStat29() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat29.loe(SearchplayerVo.getDetailStat29());
			builder.and(detailStat);
		}

		// GK 다이빙
		if (Integer.valueOf(SearchplayerVo.getDetailStat30()) != null && SearchplayerVo.getDetailStat30() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat30.loe(SearchplayerVo.getDetailStat30());
			builder.and(detailStat);
		}

		// GK 핸들링
		if (Integer.valueOf(SearchplayerVo.getDetailStat31()) != null && SearchplayerVo.getDetailStat31() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat31.loe(SearchplayerVo.getDetailStat31());
			builder.and(detailStat);
		}

		// GK 킥
		if (Integer.valueOf(SearchplayerVo.getDetailStat32()) != null && SearchplayerVo.getDetailStat32() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat32.loe(SearchplayerVo.getDetailStat32());
			builder.and(detailStat);
		}
		// GK 반응속도
		if (Integer.valueOf(SearchplayerVo.getDetailStat33()) != null && SearchplayerVo.getDetailStat33() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat33.loe(SearchplayerVo.getDetailStat33());
			builder.and(detailStat);
		}

		// GK 위치 선정
		if (Integer.valueOf(SearchplayerVo.getDetailStat34()) != null && SearchplayerVo.getDetailStat34() != 0) {
			BooleanExpression detailStat = qplayerVo.detailStat34.loe(SearchplayerVo.getDetailStat34());
			builder.and(detailStat);
		}

		// 클럽이름
		if (!SearchplayerVo.getClubName().isEmpty() && !SearchplayerVo.getClubName().get(0).equals(""))
			builder.and(qplayerVo.clubName.any().in(SearchplayerVo.getClubName()));
		
		Sort sort = Sort.by("ovr").descending();
		Predicate predicate = builder.getValue();
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<PlayerVo> page = playerMongoDBRepository.findAll(predicate, pageRequest);
		List<PlayerVo> result = page.getContent();
		
		
		
		Gson gson = new Gson();
		String listJson = gson.toJson(result, List.class).toString();
		log.info("########## result : " + listJson);
		log.info("########## size : " + result.size());

		return result;

	}
}
