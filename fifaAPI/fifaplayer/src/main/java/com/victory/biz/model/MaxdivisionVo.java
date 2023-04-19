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
public class MaxdivisionVo {

	//매치 종류 (/metadata/matchtype API 참고)
	private Integer matchType;
	//등급 식별자
	//(공식경기 : /metadata/division API 참고
	//볼타모드 : /metadata/division_volta API 참고)
	private Integer division;
	//최고등급 달성일자 (ex. 2019-05-13T18:03:10)
	private String achievementDate	;
}
