package com.victory.biz.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.QTeamcolorVo;
import com.victory.biz.model.SpidVo;
import com.victory.biz.model.TeamcolorVo;
import com.victory.system.util.HttpUrlConnectUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CrawlingService {

	@Autowired
	private MongoDBService mongoDBService;

	public void test(String id) {
		try {
			PlayerVo player = mongoDBService.selPlayer(id);
			log.info("null 아님");
		} catch (NoSuchElementException e) {
			log.info("null임");
		}

		log.info("이어서 진행");

	}

	public void setPlayerCrawling(String id) {
		log.info("in service");
		List<SpidVo> list = mongoDBService.selectSpidAll();
		log.info("find all");
		Boolean isit = false;
		int count = 1;
		for (SpidVo spidVo : list) {

			try {
				PlayerVo player = mongoDBService.selPlayer(spidVo.getKey() + "");

				isit = false;
				log.info("not insert");
			} catch (NoSuchElementException e) {
				isit = true;

				log.info("insert");
				log.info("이름 : " + spidVo.getName());
				log.info("key : " + spidVo.getKey());
			}

			if (isit) {
				try {

					Document doc;
					log.info("Id : " + spidVo.getKey());
					log.info("name : " + spidVo.getName());
					log.info("count : " + count++);

					doc = Jsoup.connect("https://fifaonline4.nexon.com/DataCenter/PlayerInfo?spid=" + spidVo.getKey())
							.get();
					PlayerVo playerVo = new PlayerVo();

					playerVo.setId(spidVo.getKey() + "");
					playerVo.setName(doc.select("div.name").get(0).text());
					playerVo.setSeason(doc.select("div.season_match").select(".season").select("[checked]").attr("id"));
					playerVo.setPaySide(Integer.valueOf(doc.select("div.pay_side").text()));

					Elements mainPosition = doc.select("div.info_ab").select(".position");
					int i = 1;
					List<String> MPlist = new ArrayList<>();
					List<Integer> MPvlist = new ArrayList<>();
					for (Element element : mainPosition) {
						MPlist.add(element.select(".position").select(".txt").text());

					}
					playerVo.setMainPosition(MPlist);

					playerVo.setBirth(doc.select("div.info_etc").select(".birth").text());
					playerVo.setHeight(
							Integer.valueOf(doc.select("div.info_etc").select(".height").text().replace("cm", "")));
					playerVo.setWeight(
							Integer.valueOf(doc.select("div.info_etc").select(".weight").text().replace("kg", "")));
					playerVo.setPhysical(doc.select("div.info_etc").select(".physical").text());
					playerVo.setSkill(doc.select("div.info_etc").select(".skill").text());
					String result = doc.select("div.info_etc").select(".foot").text();

					playerVo.setLFoot(Integer.valueOf(result.substring(1, 2)));
					playerVo.setRFoot(Integer.valueOf(result.substring(6, 7)));

					playerVo.setFame(doc.select("div.info_etc").select(".season").text());
					playerVo.setNation(doc.select("div.info_team").select(".nation").select(".txt").text());

					Elements skill_wrap = doc.select("div.skill_wrap").select("span");

					i = 1;
					List<String> skilllist = new ArrayList<>();
					for (Element element : skill_wrap) {
						skilllist.add(element.text());
					}

					playerVo.setTraits(skilllist);

					List<String> positionNlist = new ArrayList<>();
					List<Integer> positionVlist = new ArrayList<>();
					Elements position_ovr = doc.select("div.ovr_set").select(".position");
					i = 1;
					int j = 1;
					// element.removeClass("position").removeClass("value").className()
					for (Element element : position_ovr) {

						for (String data : MPlist) {
							if (data.toLowerCase().equals(
									element.removeClass("position").removeClass("value").className().toString())) {
								MPvlist.add((Integer.valueOf(element.text()) + 3));
							}
						}
						positionNlist.add(element.removeClass("position").removeClass("value").className().toString());
						positionVlist.add(Integer.valueOf(element.text()) + 3);

					}
					playerVo.setOvr(Collections.max(MPvlist));
					playerVo.setMainPositionValue(MPvlist);
					playerVo.setPositionName(positionNlist);
					playerVo.setPositionValue(positionVlist);

					List<String> mainstatNlist = new ArrayList<>();
					List<Integer> mainstatVlist = new ArrayList<>();

					Elements content_middle = doc.select("div.content_middle").select(".ab");
					for (Element element : content_middle) {
						mainstatNlist.add(element.select(".txt").text());
						mainstatVlist.add((Integer.valueOf(element.select(".value").text()) + 3));
					}

					i = 1;
					Elements content_bottom = doc.select("div.content_bottom").select(".ab");
					for (Element element : content_bottom) {

						if (i == 1)
							playerVo.setDetailStat1((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 2)
							playerVo.setDetailStat2((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 3)
							playerVo.setDetailStat3((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 4)
							playerVo.setDetailStat4((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 5)
							playerVo.setDetailStat5((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 6)
							playerVo.setDetailStat6((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 7)
							playerVo.setDetailStat7((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 8)
							playerVo.setDetailStat8((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 9)
							playerVo.setDetailStat9((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 10)
							playerVo.setDetailStat10((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 11)
							playerVo.setDetailStat11((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 12)
							playerVo.setDetailStat12((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 13)
							playerVo.setDetailStat13((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 14)
							playerVo.setDetailStat14((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 15)
							playerVo.setDetailStat15((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 16)
							playerVo.setDetailStat16((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 17)
							playerVo.setDetailStat17((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 18)
							playerVo.setDetailStat18((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 19)
							playerVo.setDetailStat19((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 20)
							playerVo.setDetailStat20((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 21)
							playerVo.setDetailStat21((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 22)
							playerVo.setDetailStat22((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 23)
							playerVo.setDetailStat23((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 24)
							playerVo.setDetailStat24((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 25)
							playerVo.setDetailStat25((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 26)
							playerVo.setDetailStat26((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 27)
							playerVo.setDetailStat27((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 28)
							playerVo.setDetailStat28((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 29)
							playerVo.setDetailStat29((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 30)
							playerVo.setDetailStat30((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 31)
							playerVo.setDetailStat31((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 32)
							playerVo.setDetailStat32((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 33)
							playerVo.setDetailStat33((Integer.valueOf(element.select(".value").text()) + 3));
						if (i == 34)
							playerVo.setDetailStat34((Integer.valueOf(element.select(".value").text()) + 3));
						i++;
					}

					List<String> clubYear = new ArrayList<>();
					List<String> clubName = new ArrayList<>();
					Elements club = doc.select("div.data_detail_club").select(".data_table").select("li");
					for (Element element : club) {

						clubYear.add(element.select(".year").text());
						clubName.add(element.select(".club").text());
					}

					playerVo.setClubYear(clubYear);
					playerVo.setClubName(clubName);

					playerVo = setTeamColor(playerVo);

					mongoDBService.insertPlayer(playerVo);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void setTeamcolorCrawling(String typeCd, String type) {

		try {
			log.info("in service");
			Document doc = Jsoup
					//관계 = 32, 스페셜 = 13, 강화 = 21, 국가 = 12, 클럽 = 11
					.connect("https://fifaonline4.inven.co.kr/dataninfo/teamcolor2/?searchword=&tcolor2type%5B%5D=" + typeCd)
					.get();
			Elements data = doc.select("div.board_teamcolor").select(".table").select("tbody").select("tr");
			int Count = 1;
			for (Element param : data) {

				TeamcolorVo teamcolorVo = new TeamcolorVo();
				// key
				teamcolorVo.setKey(param.attr("data-code").substring(0, param.attr("data-code").length() - 2));
				// type
				// 관계 = relation, 스페셜 = special, 강화 = grade,국가 = nation, 클럽 = club
				teamcolorVo.setType(type);

				// 이름
				String name = param.select(".name").text();
				teamcolorVo.setName(name.substring(6, name.length()));
				log.info("in teamcolor2 code");
				Document lv = Jsoup
						.connect(
								"https://fifaonline4.inven.co.kr/dataninfo/teamcolor2/?code=" + param.attr("data-code"))
						.get();

				String level = lv.select(".dniAdShow").select(".count").text();
				// 레벨

				teamcolorVo.setLevel(param.attr("data-tcolor2level"));
				teamcolorVo.setPersonnel(level.substring(0, 1));

				// 능력치
				Elements detailStat = param.select(".text_center").select("p");
				for (Element pa : detailStat) {
					String stat = pa.text();

					teamcolorVo = changeString(teamcolorVo, stat.subSequence(0, stat.indexOf("+") - 1).toString(),
							Integer.valueOf(stat.subSequence(stat.indexOf("+") + 1, stat.length()).toString()));
				}
				log.info("in datacenter PlayerList");
				if(!type.equals("grade")) {
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
				}
				log.info("name : " + teamcolorVo.getName());
				log.info("level : " + teamcolorVo.getLevel());
				log.info("Personnel : " + teamcolorVo.getPersonnel());
				log.info("count : " + Count++);

				mongoDBService.insertTeamcolor(teamcolorVo);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public PlayerVo setTeamColor(PlayerVo playerVo) {

		QTeamcolorVo qTeamcolorVo = QTeamcolorVo.teamcolorVo;
		BooleanBuilder builder = new BooleanBuilder();

		builder.and(qTeamcolorVo.spid.contains(playerVo.getId()));

		Predicate predicate = builder.getValue();

		List<TeamcolorVo> list = mongoDBService.selTeamcolorPlayer(predicate);

		playerVo.setTeamColor(list);

		return playerVo;

	}

	public Map<String, String> getMap(String teamcolorkey) {

		Gson gson = new Gson();
		String dat = "{\r\n" + "\"n1Confederation\": \"0\",\r\n" + "\"n4LeagueId\": \"0\",\r\n"
				+ "\"strSeason\": \"\",\r\n" + "\"strPosition\": \"\",\r\n" + "\"strPhysical\": \"\",\r\n"
				+ "\"n1LeftFootAblity\": \"0\",\r\n" + "\"n1RightFootAblity\": \"0\",\r\n"
				+ "\"n1SkillMove\": \"0\",\r\n" + "\"n1InterationalRep\": \"0\",\r\n" + "\"n4BirthMonth\": \"0\",\r\n"
				+ "\"n4BirthDay\": \"0\",\r\n" + "\"n4TeamId\": \"0\",\r\n" + "\"n4NationId\": \"0\",\r\n"
				+ "\"strAbility1\": \"\",\r\n" + "\"strAbility2\": \"\",\r\n" + "\"strAbility3\": \"\",\r\n"
				+ "\"strTrait1\": \"\",\r\n" + "\"strTrait2\": \"\",\r\n" + "\"strTrait3\": \"\",\r\n"
				+ "\"strTraitNon1\": \"\",\r\n" + "\"strTraitNon2\": \"\",\r\n" + "\"strTraitNon3\": \"\",\r\n"
				+ "\"n1Strong\": \"1\",\r\n" + "\"n1Grow\": \"0\",\r\n" + "\"n1TeamColor\": \"0\",\r\n"
				+ "\"strSkill1\": \"sprintspeed\",\r\n" + "\"strSkill2\": \"acceleration\",\r\n"
				+ "\"strSkill3\": \"strength\",\r\n" + "\"strSkill4\": \"stamina\",\r\n"
				+ "\"strSearchStatus\": \"off\",\r\n" + "\"strOrderby\": \"\",\r\n" + "\"teamcolorid\": \"40082\",\r\n"
				+ "\"strTeamColorCategory\": \"affiliation\",\r\n" + "\"n1History\": \"0\",\r\n"
				+ "\"n4PlayYear\": \"0\",\r\n" + "\"strPlayerName\": \"\",\r\n" + "\"strTeamName\": \"\",\r\n"
				+ "\"strNationName\": \"\",\r\n" + "\"strTeamColorName\": \"\",\r\n" + "\"n4OvrMin\": \"0\",\r\n"
				+ "\"n4OvrMax\": \"200\",\r\n" + "\"n4SalaryMin\": \"4\",\r\n" + "\"n4SalaryMax\": \"99\",\r\n"
				+ "\"n8PlayerGrade1Min\": \"0\",\r\n" + "\"n8PlayerGrade1Max\": \"99999\",\r\n"
				+ "\"n1Ability1Min\": \"40\",\r\n" + "\"n1Ability1Max\": \"200\",\r\n"
				+ "\"n1Ability2Min\": \"40\",\r\n" + "\"n1Ability2Max\": \"200\",\r\n"
				+ "\"n1Ability3Min\": \"40\",\r\n" + "\"n1Ability3Max\": \"200\",\r\n"
				+ "\"n4BirthYearMin\": \"1900\",\r\n" + "\"n4BirthYearMax\": \"2010\",\r\n"
				+ "\"n4HeightMin\": \"140\",\r\n" + "\"n4HeightMax\": \"208\",\r\n" + "\"n4WeightMin\": \"50\",\r\n"
				+ "\"n4WeightMax\": \"110\",\r\n" + "\"n4AvgPointMin\": \"0\",\r\n" + "\"n4AvgPointMax\": \"10\",\r\n"
				+ "\"n4PageNo\": \"1rd=0.7278073747154667\"\r\n" + "}";

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
				teamcolorVo.setDetailStat34(i);
				break;
			default:
				break;
			}
		}

		return teamcolorVo;
	}

}
