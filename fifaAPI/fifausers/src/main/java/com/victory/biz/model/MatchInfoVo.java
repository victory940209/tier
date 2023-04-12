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
public class MatchInfoVo {

	//	유저 고유 식별자
	private String accessId;
	//	유저 닉네임
	private String nickname;
	//	매치 결과 상세 정보
	private MatchDetailVo matchDetail;
}
