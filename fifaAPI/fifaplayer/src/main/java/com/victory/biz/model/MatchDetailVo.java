package com.victory.biz.model;

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
public class MatchDetailVo {

	private String seasonId;       //시즌 ID
	private String matchResult;    //매치 결과 (“승”, “무”, “패”)
	private String matchEndType;   //매치종료 타입 (0: 정상종료, 1: 몰수승, 2:몰수패)
	private String systemPause;    //게임 일시정지 수
	private String foul;           //파울 수
	private String injury;         //부상 수
	private String redCards;       //받은 레드카드 수
	private String yellowCards;    //받은 옐로카드 수
	private String dribble;        //드리블 거리(야드)
	private String cornerKick;     //코너킥 수
	private String possession;     //점유율
	private String offsideCount;   //오프사이드 수
	private String averageRating;  //경기 평점
	private String controller;     //사용한 컨트롤러 타입 (keyboard / pad / etc 중 1)
}
