package com.victory.biz.model;

import javax.validation.Valid;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Schema
public class SearchTeamcolorVo {

	// key
	private String key;
	// 클럽 club, 국가 nation, 스페셜 special, 관계 relation, 강화 grade
	private String type;
	// 팀컬러 이름
	private String name;
	// 팀컬러 이름
	private Boolean distinct;

}
