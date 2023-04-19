package com.victory.biz.model;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
@JsonSerializableSchema
public class UserVo {
	// 유저 고유 식별자
	private String accessId;
	// 유저 닉네임
	private String nickname;
	// 유저 레벨
	private Integer level;

}
