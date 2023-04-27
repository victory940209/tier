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
public class PriceVo {

	//현재 가치(BP)
	String toValue;
	//Max 가치
	String maxValue;
	//Min 가치
	String minValue;
	//날짜
	List<String> time;
	//각 가치
	List<String> value;
}
