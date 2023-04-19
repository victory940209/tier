package com.victory.biz.model;

import java.util.List;

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
public class SearchPlayerVo {
	// spid
	@Schema(name = "spid", example = "", description = "spid")
	private String key;
	// 이름
	@Schema(name = "name", example = "", description = "이름")
	private String name;
	// 시즌
	@Schema(name = "season", example = "", description = "시즌")
	private String season;
	// 급여
	@Schema(name = "paySide", example = "", description = "급여")
	private int paySide;
	// 오버롤
	@Schema(name = "ovr", example = "", description = "오버롤")
	private int ovr;
	// 주포지션
	@Schema(name = "mainPosition", example = "", description = "주포지션")
	private List<String> mainPosition;
	// 나이
	@Schema(name = "birth", example = "", description = "나이")
	private String birth;
	// 키
	@Schema(name = "height", example = "", description = "키")
	private int height;
	// 개인기
	@Schema(name = "skill", example = "", description = "개인기")
	private String skill;
	// 왼발
	@Schema(name = "lFoot", example = "", description = "왼발")
	private int lFoot;
	// 오른발
	@Schema(name = "rFoot", example = "", description = "오른발")
	private int rFoot;
	// 국가
	@Schema(name = "nation", example = "", description = "국가")
	private String nation;
	// 특성
	@Schema(name = "traits", example = "", description = "특성")
	private List<String> traits;
	// 속력
	@Schema(name = "detailStat1", example = "", description = "속력")
	private int detailStat1;
	// 가속력
	@Schema(name = "detailStat2", example = "", description = "가속력")
	private int detailStat2;
	// 골 결정력
	@Schema(name = "detailStat3", example = "", description = "골 결정력")
	private int detailStat3;
	// 슛 파워
	@Schema(name = "detailStat4", example = "", description = "슛 파워")
	private int detailStat4;
	// 중거리 슛
	@Schema(name = "detailStat5", example = "", description = "중거리 슛")
	private int detailStat5;
	// 위치 선정
	@Schema(name = "detailStat6", example = "", description = "위치 선정")
	private int detailStat6;
	// 발리슛
	@Schema(name = "detailStat7", example = "", description = "발리슛")
	private int detailStat7;
	// 페널티 킥
	@Schema(name = "detailStat8", example = "", description = "페널티 킥")
	private int detailStat8;
	// 짧은 패스
	@Schema(name = "detailStat9", example = "", description = "짧은 패스")
	private int detailStat9;
	// 시야
	@Schema(name = "detailStat10", example = "", description = "시야")
	private int detailStat10;
	// 크로스
	@Schema(name = "detailStat11", example = "", description = "크로스")
	private int detailStat11;
	// 긴 패스
	@Schema(name = "detailStat12", example = "", description = "긴 패스")
	private int detailStat12;
	// 프리킥
	@Schema(name = "detailStat13", example = "", description = "프리킥")
	private int detailStat13;
	// 커브
	@Schema(name = "detailStat14", example = "", description = "커브")
	private int detailStat14;
	// 드리블
	@Schema(name = "detailStat15", example = "", description = "드리블")
	private int detailStat15;
	// 볼 컨트롤
	@Schema(name = "detailStat16", example = "", description = "볼 컨트롤")
	private int detailStat16;
	// 민첩성
	@Schema(name = "detailStat17", example = "", description = "민첩성")
	private int detailStat17;
	// 밸런스
	@Schema(name = "detailStat18", example = "", description = "밸런스")
	private int detailStat18;
	// 반응 속도
	@Schema(name = "detailStat19", example = "", description = "반응 속도")
	private int detailStat19;
	// 대인 수비
	@Schema(name = "detailStat20", example = "", description = "대인 수비")
	private int detailStat20;
	// 태클
	@Schema(name = "detailStat21", example = "", description = "태클")
	private int detailStat21;
	// 가로채기
	@Schema(name = "detailStat22", example = "", description = "가로채기")
	private int detailStat22;
	// 헤더
	@Schema(name = "detailStat23", example = "", description = "헤더")
	private int detailStat23;
	// 슬라이딩 태클
	@Schema(name = "detailStat24", example = "", description = "슬라이딩 태클")
	private int detailStat24;
	// 몸싸움
	@Schema(name = "detailStat25", example = "", description = "몸싸움")
	private int detailStat25;
	// 스태미너
	@Schema(name = "detailStat26", example = "", description = "스태미너")
	private int detailStat26;
	// 적극성
	@Schema(name = "detailStat27", example = "", description = "적극성")
	private int detailStat27;
	// 점프
	@Schema(name = "detailStat28", example = "", description = "점프")
	private int detailStat28;
	// 침착성
	@Schema(name = "detailStat29", example = "", description = "침착성")
	private int detailStat29;
	// GK 다이빙
	@Schema(name = "detailStat30", example = "", description = "GK 다이빙")
	private int detailStat30;
	// GK 핸들링
	@Schema(name = "detailStat31", example = "", description = "GK 핸들링")
	private int detailStat31;
	// GK 킥
	@Schema(name = "detailStat32", example = "", description = "GK 킥")
	private int detailStat32;
	// GK 반응속도
	@Schema(name = "detailStat33", example = "", description = "GK 반응속도")
	private int detailStat33;
	// GK 위치 선정
	@Schema(name = "detailStat34", example = "", description = "GK 위치 선정")
	private int detailStat34;
	// 클럽이름
	@Schema(name = "clubName", example = "", description = "클럽이름")
	private List<String> clubName;
}
