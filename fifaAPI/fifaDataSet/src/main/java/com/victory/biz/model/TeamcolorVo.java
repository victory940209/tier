package com.victory.biz.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Schema
@AllArgsConstructor
@NoArgsConstructor
@JsonSerializableSchema
@Document(collection = "teamcolor")
public class TeamcolorVo {

	// key
	private String key;
	// 클럽 club, 국가 nation, 스페셜 special, 관계 relation, 강화 grade
	private String type;
	// 팀컬러 이름
	private String name;
	// 인원
	private String personnel;
	// 팀컬러 레벨
	private String level;
	// image 파일 이름
	private String image;
	// 팀컬러 적용 spid
	private List<String> spid;
	// 속력
	private int detailStatAll;
	// 속력
	private int detailStat1;
	// 가속력
	private int detailStat2;
	// 골 결정력
	private int detailStat3;
	// 슛 파워
	private int detailStat4;
	// 중거리 슛
	private int detailStat5;
	// 위치 선정
	private int detailStat6;
	// 발리슛
	private int detailStat7;
	// 페널티 킥
	private int detailStat8;
	// 짧은 패스
	private int detailStat9;
	// 시야
	private int detailStat10;
	// 크로스
	private int detailStat11;
	// 긴 패스
	private int detailStat12;
	// 프리킥
	private int detailStat13;
	// 커브
	private int detailStat14;
	// 드리블
	private int detailStat15;
	// 볼 컨트롤
	private int detailStat16;
	// 민첩성
	private int detailStat17;
	// 밸런스
	private int detailStat18;
	// 반응 속도
	private int detailStat19;
	// 대인 수비
	private int detailStat20;
	// 태클
	private int detailStat21;
	// 가로채기
	private int detailStat22;
	// 헤더
	private int detailStat23;
	// 슬라이딩 태클
	private int detailStat24;
	// 몸싸움
	private int detailStat25;
	// 스태미너
	private int detailStat26;
	// 적극성
	private int detailStat27;
	// 점프
	private int detailStat28;
	// 침착성
	private int detailStat29;
	// GK 다이빙
	private int detailStat30;
	// GK 핸들링
	private int detailStat31;
	// GK 킥
	private int detailStat32;
	// GK 반응속도
	private int detailStat33;
	// GK 위치 선정
	private int detailStat34;

}
