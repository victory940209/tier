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
public class MarketVo {


	//거래일자 (ex. 2019-05-13T18:03:10)
	//	구매(buy)일	경우	구매 등록 시점
	//	판매(sell)일	경우	판매 완료 시점
	String tradeDate;
	//거래 고유 식별자
	String saleSn;
	//선수 고유 식별자 (/metadata/spid API 참고)
	String spid;
	//거래 선수 강화 등급
	String grade;
	//거래 선수 가치(BP)
	String value;
	//가격정보
	PriceVo priceVo;
}
