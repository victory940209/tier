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
public class MatchVo {

	//	매치 고유 식별자
	private String matchId;
	//	매치 일자 (ex. 2019-05-13T18:03:10)
	private String matchDate;
	// 	매치 종류 (/metadata/matchtype API 참고)
	private Integer matchType;
	//	매치 참여 플레이어별 매치 내용 상세 리스트
	private List<MatchInfoVo> matchInfo ;
}
