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
public class SearchInfoVo {

	private String matchtype; //매치 종류 (/metadata/matchtype API 참고)
	private String tradetype; //거래 종류 (구입 : buy, 판매 : sell)
	private String offset;	//리스트에서 가져올 시작 위치
	private String limit;	//리스트에서 가져올 갯수(최대 100 개)
	private String orderby; //매치 기록의 정렬 순서 (asc : 가장 오래된 매치부터 매치목록 반환, desc : 가장 최근 플레이한 매치부터 매치목록 반환)

}
