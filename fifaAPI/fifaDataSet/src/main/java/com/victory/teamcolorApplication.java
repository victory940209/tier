package com.victory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpHeaders;

import com.google.gson.Gson;
import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.TeamcolorVo;
import com.victory.system.util.HttpUrlConnectUtil;

public class teamcolorApplication {

	public static void main(String[] args) {

		try {
			Document doc = Jsoup
					.connect("https://fifaonline4.inven.co.kr/dataninfo/teamcolor2/?searchword=&tcolor2type%5B%5D=32")
					.get();
			Elements data = doc.select("div.board_teamcolor").select(".table").select("tbody").select("tr");
			
			int Count = 1;
	
			for (Element param : data) {
				
				
				TeamcolorVo teamcolorVo = new TeamcolorVo();
				// key
				teamcolorVo.setKey(param.attr("data-code").substring(0, param.attr("data-code").length() - 2));
				// type
				teamcolorVo.setType("relation");
				// 이름
				String name = param.select(".name").text();
				teamcolorVo.setName(name.substring(6, name.length()));
				
				Document lv = Jsoup
						.connect("https://fifaonline4.inven.co.kr/dataninfo/teamcolor2/?code=" + param.attr("data-code"))
						.get();
		
				String level = lv.select(".dniAdShow").select(".count").text();
				// 레벨

				teamcolorVo.setLevel(param.attr("data-tcolor2level"));
				teamcolorVo.setPersonnel(level.substring(0,1));

				// 능력치
				Elements detailStat = param.select(".text_center").select("p");
				for (Element pa : detailStat) {
					String stat = pa.text();

					teamcolorVo = changeString(teamcolorVo, stat.subSequence(0, stat.indexOf("+") - 1).toString(),
							Integer.valueOf(stat.subSequence(stat.indexOf("+") + 1, stat.length()).toString()));
				}

				Map<String, String> asd = getMap(teamcolorVo.getKey());
				Document paramt = Jsoup.connect("https://fifaonline4.nexon.com/datacenter/PlayerList").data(asd).post();
				Elements f4teamcolorPlayer = paramt.select("div.tr");

				List<String> spid = new ArrayList<>();
				for (Element pl : f4teamcolorPlayer) {
					String playerKey = pl.select("div.players_utils").get(0).removeClass("players_utils").className()
							.toString();
					spid.add(playerKey.replace("players_utils_", ""));

					// 가격은
					// 이름이랑 시즌 넣으면 됨
					// strPlayerName strSeason
//				System.out.println("가격 : " + param.select("span.span_bp1").text());

				}
				teamcolorVo.setSpid(spid);

				Gson gson = new Gson();
				String Json = gson.toJson(teamcolorVo, TeamcolorVo.class).toString();
				System.out.println("teamcolorVo : " + Json);
				
			}
			
//			}
//			Document docff = Jsoup.connect(
//					"https://fifaonline4.nexon.com/datacenter/teamcolor?strTeamColorCategory=%2Caffiliation%2C&strTeamColorType=%2Cclub%2C&strTeamColorName=")
//					.get();
//			
//
//			
//			
//			Elements f4teamcolor = docff.select("div.teamcolor_item_list").select(".teamcolor_item");
//			System.out.println(f4teamcolor.get(0));

//			for (Element teamcolor : f4teamcolor) {
//
//				//key matching
//
//				String key = teamcolor.select(".btn_detail_link").attr("onclick");
//				String sttkey = key.substring(key.indexOf("(")+1,key.indexOf(")"));
//				
//				System.out.println("key : " + sttkey);

//
//				Map<String, String> asd = getMap("4008203");
//				Document paramt = Jsoup.connect("https://fifaonline4.nexon.com/datacenter/PlayerList").data(asd).post();
//				Elements f4teamcolorPlayer = paramt.select("div.tr");
//				System.out.println(f4teamcolorPlayer.get(0));
//				for (Element param : f4teamcolorPlayer) {
//					String playerKey =  param.select("div.players_utils").get(0).removeClass("players_utils").className().toString();
//					
//					System.out.println("Playerkey : " +playerKey.replace("players_utils_", ""));
//					//가격은
//					//이름이랑        시즌 넣으면 됨
//					//strPlayerName strSeason
////					System.out.println("가격 : " + param.select("span.span_bp1").text());
//					
//					System.out.println("-----------------------------------------");
//				}
//			}
			
						
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static Map<String, String> getMap(String teamcolorkey) {
	
		 Gson gson = new Gson();
		 String dat = "{\r\n"
		 		+ "\"n1Confederation\": \"0\",\r\n"
		 		+ "\"n4LeagueId\": \"0\",\r\n"
		 		+ "\"strSeason\": \"\",\r\n"
		 		+ "\"strPosition\": \"\",\r\n"
		 		+ "\"strPhysical\": \"\",\r\n"
		 		+ "\"n1LeftFootAblity\": \"0\",\r\n"
		 		+ "\"n1RightFootAblity\": \"0\",\r\n"
		 		+ "\"n1SkillMove\": \"0\",\r\n"
		 		+ "\"n1InterationalRep\": \"0\",\r\n"
		 		+ "\"n4BirthMonth\": \"0\",\r\n"
		 		+ "\"n4BirthDay\": \"0\",\r\n"
		 		+ "\"n4TeamId\": \"0\",\r\n"
		 		+ "\"n4NationId\": \"0\",\r\n"
		 		+ "\"strAbility1\": \"\",\r\n"
		 		+ "\"strAbility2\": \"\",\r\n"
		 		+ "\"strAbility3\": \"\",\r\n"
		 		+ "\"strTrait1\": \"\",\r\n"
		 		+ "\"strTrait2\": \"\",\r\n"
		 		+ "\"strTrait3\": \"\",\r\n"
		 		+ "\"strTraitNon1\": \"\",\r\n"
		 		+ "\"strTraitNon2\": \"\",\r\n"
		 		+ "\"strTraitNon3\": \"\",\r\n"
		 		+ "\"n1Strong\": \"1\",\r\n"
		 		+ "\"n1Grow\": \"0\",\r\n"
		 		+ "\"n1TeamColor\": \"0\",\r\n"
		 		+ "\"strSkill1\": \"sprintspeed\",\r\n"
		 		+ "\"strSkill2\": \"acceleration\",\r\n"
		 		+ "\"strSkill3\": \"strength\",\r\n"
		 		+ "\"strSkill4\": \"stamina\",\r\n"
		 		+ "\"strSearchStatus\": \"off\",\r\n"
		 		+ "\"strOrderby\": \"\",\r\n"
		 		+ "\"teamcolorid\": \"40082\",\r\n"
		 		+ "\"strTeamColorCategory\": \"affiliation\",\r\n"
		 		+ "\"n1History\": \"0\",\r\n"
		 		+ "\"n4PlayYear\": \"0\",\r\n"
		 		+ "\"strPlayerName\": \"\",\r\n"
		 		+ "\"strTeamName\": \"\",\r\n"
		 		+ "\"strNationName\": \"\",\r\n"
		 		+ "\"strTeamColorName\": \"\",\r\n"
		 		+ "\"n4OvrMin\": \"0\",\r\n"
		 		+ "\"n4OvrMax\": \"200\",\r\n"
		 		+ "\"n4SalaryMin\": \"4\",\r\n"
		 		+ "\"n4SalaryMax\": \"99\",\r\n"
		 		+ "\"n8PlayerGrade1Min\": \"0\",\r\n"
		 		+ "\"n8PlayerGrade1Max\": \"99999\",\r\n"
		 		+ "\"n1Ability1Min\": \"40\",\r\n"
		 		+ "\"n1Ability1Max\": \"200\",\r\n"
		 		+ "\"n1Ability2Min\": \"40\",\r\n"
		 		+ "\"n1Ability2Max\": \"200\",\r\n"
		 		+ "\"n1Ability3Min\": \"40\",\r\n"
		 		+ "\"n1Ability3Max\": \"200\",\r\n"
		 		+ "\"n4BirthYearMin\": \"1900\",\r\n"
		 		+ "\"n4BirthYearMax\": \"2010\",\r\n"
		 		+ "\"n4HeightMin\": \"140\",\r\n"
		 		+ "\"n4HeightMax\": \"208\",\r\n"
		 		+ "\"n4WeightMin\": \"50\",\r\n"
		 		+ "\"n4WeightMax\": \"110\",\r\n"
		 		+ "\"n4AvgPointMin\": \"0\",\r\n"
		 		+ "\"n4AvgPointMax\": \"10\",\r\n"
		 		+ "\"n4PageNo\": \"1rd=0.7278073747154667\"\r\n"
		 		+ "}";
		 
		 
		 Map<String, String> mapData = gson.fromJson(dat, Map.class);
		
		 mapData.put("teamcolorid", teamcolorkey);
		 
		 
		return mapData;
		
	}
	
	
	
	
	
	
	
	
	
	

	public static TeamcolorVo changeString(TeamcolorVo teamcolorVo, String data, int i) {
		if (!data.equals("")) {

			switch (data) {
			case "전체 능력치":
				teamcolorVo.setDetailStatAll(i);
				break;
			case "속력":
				teamcolorVo.setDetailStat1(i);
				break;
			case "가속력":
				teamcolorVo.setDetailStat2(i);
				break;
			case "골 결정력":
				teamcolorVo.setDetailStat3(i);
				break;
			case "슛 파워":
				teamcolorVo.setDetailStat4(i);
				break;
			case "중거리 슛":
				teamcolorVo.setDetailStat5(i);
				break;
			case "위치 선정":
				teamcolorVo.setDetailStat6(i);
				break;
			case "발리슛":
				teamcolorVo.setDetailStat7(i);
				break;
			case "페널티 킥":
				teamcolorVo.setDetailStat8(i);
				break;
			case "짧은 패스":
				teamcolorVo.setDetailStat9(i);
				break;
			case "시야":
				teamcolorVo.setDetailStat10(i);
				break;
			case "크로스":
				teamcolorVo.setDetailStat11(i);
				break;
			case "긴 패스":
				teamcolorVo.setDetailStat12(i);
				break;
			case "프리킥":
				teamcolorVo.setDetailStat13(i);
				break;
			case "커브":
				teamcolorVo.setDetailStat14(i);
				break;
			case "드리블":
				teamcolorVo.setDetailStat15(i);
				break;
			case "볼 컨트롤":
				teamcolorVo.setDetailStat16(i);
				break;
			case "민첩성":
				teamcolorVo.setDetailStat17(i);
				break;
			case "밸런스":
				teamcolorVo.setDetailStat18(i);
				break;
			case "반응 속도":
				teamcolorVo.setDetailStat19(i);
				break;
			case "대인 수비":
				teamcolorVo.setDetailStat20(i);
				break;
			case "태클":
				teamcolorVo.setDetailStat21(i);
				break;
			case "가로채기":
				teamcolorVo.setDetailStat22(i);
				break;
			case "헤더":
				teamcolorVo.setDetailStat23(i);
				break;
			case "슬라이딩 태클":
				teamcolorVo.setDetailStat24(i);
				break;
			case "몸싸움":
				teamcolorVo.setDetailStat25(i);
				break;
			case "스태미너":
				teamcolorVo.setDetailStat26(i);
				break;
			case "적극성":
				teamcolorVo.setDetailStat27(i);
				break;
			case "점프":
				teamcolorVo.setDetailStat28(i);
				break;
			case "침착성":
				teamcolorVo.setDetailStat29(i);
				break;
			case "GK 다이빙":
				teamcolorVo.setDetailStat30(i);
				break;
			case "GK 핸들링":
				teamcolorVo.setDetailStat31(i);
				break;
			case "GK 킥":
				teamcolorVo.setDetailStat32(i);
				break;
			case "GK 반응속도":
				teamcolorVo.setDetailStat33(i);
				break;
			case "GK 위치 선정":
				teamcolorVo.setDetailStat33(34);
				break;
			default:
				break;
			}
		}

		return teamcolorVo;
	}

}
