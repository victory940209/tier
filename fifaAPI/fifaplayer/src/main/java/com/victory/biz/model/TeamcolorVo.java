package com.victory.biz.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "teamcolor")
public class TeamcolorVo {

	// key
	private String key;
	// 클럽 club, 국가 nation, 스페셜 special, 관계 relation, 강화 grade
	private String type;
	// 팀컬러 이름
	private String name;
	// 인원
	private String personnel;
	// 팀컬러 레벨
	private String level;
	// image 파일 이름
	private String image;
	// 팀컬러 적용 spid
	private List<String> spid;


}
