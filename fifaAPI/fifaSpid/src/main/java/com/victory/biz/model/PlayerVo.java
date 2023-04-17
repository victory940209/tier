package com.victory.biz.model;

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
@Document(collection = "player")
public class PlayerVo {

	//spid
	private String id;
	//이름
	private String name;
	//시즌
	private String season;
	//급여
	private String paySide;
	//주포지션
	private String mainPosition;
	//나이
	private String birth;
	//키
	private String height;
	//몸무게
	private String weight;
	//피지컬
	private String physical;
	//개인기
	private String skill;
	//발
	private String foot;
	//명성
	private String fame;
	//국가
	private String nation;
	//특성
	private String traits;
	//st 스탯
	private String positionValue1;
	//lw 스탯
	private String positionValue2;
	//cf 스탯
	private String positionValue3;
	//rw 스탯
	private String positionValue4;
	//cam 스탯
	private String positionValue5;
	//lm 스탯
	private String positionValue6;
	//cm 스탯
	private String positionValue7;
	//rm 스탯
	private String positionValue8;
	//cdm 스탯
	private String positionValue9;
	//lwb 스탯
	private String positionValue10;
	//cb 스탯
	private String positionValue11;
	//rwb 스탯
	private String positionValue12;
	//lb 스탯
	private String positionValue13;
	//sw 스탯
	private String positionValue14;
	//rb 스탯
	private String positionValue15;
	//gk 스탯
	private String positionValue16;
	//주포지션 스탯
	private String mainPositionValue;
	//스피드
	private String mainStat1;
	//슛
	private String mainStat2;
	//패스
	private String mainStat3;
	//드리블
	private String mainStat4;
	//수비
	private String mainStat5;
	//피지컬
	private String mainStat6;
	//속력
	private String detailStat1;
	//가속력
	private String detailStat2;
	//골 결정력
	private String detailStat3;
	//슛 파워
	private String detailStat4;
	//중거리 슛
	private String detailStat5;
	//위치 선정
	private String detailStat6;
	//발리슛
	private String detailStat7;
	//페널티 킥
	private String detailStat8;
	//짧은 패스
	private String detailStat9;
	//시야
	private String detailStat10;
	//크로스
	private String detailStat11;
	//긴 패스
	private String detailStat12;
	//프리킥
	private String detailStat13;
	//커브
	private String detailStat14;
	//드리블
	private String detailStat15;
	//볼 컨트롤
	private String detailStat16;
	//민첩성
	private String detailStat17;
	//밸런스
	private String detailStat18;
	//반응 속도
	private String detailStat19;
	//대인 수비
	private String detailStat20;
	//태클
	private String detailStat21;
	//가로채기
	private String detailStat22;
	//헤더
	private String detailStat23;
	//슬라이딩 태클
	private String detailStat24;
	//몸싸움
	private String detailStat25;
	//스태미너
	private String detailStat26;
	//적극성
	private String detailStat27;
	//점프
	private String detailStat28;
	//침착성
	private String detailStat29;
	//GK 다이빙
	private String detailStat30;
	//GK 핸들링
	private String detailStat31;
	//GK 킥
	private String detailStat32;
	//GK 반응속도
	private String detailStat33;
	//GK 위치 선정
	private String detailStat34;
	//클럽년도
	private String clubYear;
	//클럽이름
	private String clubName;

}
