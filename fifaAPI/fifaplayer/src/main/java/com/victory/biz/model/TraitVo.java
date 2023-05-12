package com.victory.biz.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Schema
@JsonSerializableSchema
@NoArgsConstructor
@Document(collection = "trait")
public class TraitVo {

	private String name;

	private String image;
}
