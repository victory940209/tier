package com.victory.biz.model;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema
@JsonSerializableSchema
public class SppositionVo {

	private int spposition;

	private String desc;
}
