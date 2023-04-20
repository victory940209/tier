package com.victory.biz.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.SpidVo;
import com.victory.biz.model.TeamcolorVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CrawlingService {

	@Autowired
	private MongoDBService mongoDBService;

	public void getPlayerCrawling(String id) {

		List<SpidVo> list = mongoDBService.selectSpidAll();
		int count = 1;
		for (SpidVo spidVo : list) {
			try {
				Document doc;
				log.info("Id : " + spidVo.getKey());
				log.info("name : " + spidVo.getName());
				log.info("count : " + count++);

				doc = Jsoup.connect("https://fifaonline4.nexon.com/DataCenter/PlayerInfo?spid=" + spidVo.getKey())
						.get();
				PlayerVo playerVo = new PlayerVo();

				playerVo.setKey(spidVo.getKey() + "");
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
						if (data.toLowerCase()
								.equals(element.removeClass("position").removeClass("value").className().toString())) {
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

				mongoDBService.insertPlayer(playerVo);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void setTeamcolorCrawling(String url) {

		Document doc;
		try {
			doc = Jsoup
					.connect("https://fifaonline4.inven.co.kr/dataninfo/teamcolor2/?searchword=&tcolor2type%5B%5D=11")
					.get();
			Elements data = doc.select("div.board_teamcolor").select(".table").select("tbody").select("tr");

			for (Element param : data) {

				TeamcolorVo teamcolorVo = new TeamcolorVo();
				// key
				teamcolorVo.setKey(param.attr("data-code"));
				// type
				teamcolorVo.setType("club");
				// 이름
				String name = param.select(".name").text();
				teamcolorVo.setName(name.substring(6, name.length()));

				// 인원
				// System.out.println("personnel : " + param.attr("data-tcolor2type"));
				// 레벨
				System.out.println("level : " + param.attr("data-tcolor2level"));
				// 능력치
				Elements detailStat = param.select(".text_center").select("p");
				for (Element pa : detailStat) {
					String stat = pa.text();

					changeString(stat.subSequence(0, stat.indexOf("+") - 1).toString(),
							Integer.valueOf(stat.subSequence(stat.indexOf("+") + 1, stat.length()).toString()));
				}
				System.out.println("--------------------------------------------------");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setTeamcolorCrawling2(String url) {

		try {
			Document doc = Jsoup.connect(
					"https://fifaonline4.nexon.com/datacenter/teamcolor?strTeamColorCategory=%2Caffiliation%2C&strTeamColorType=%2Cclub%2C&strTeamColorName=")
					.get();
			Elements data = doc.select("div.teamcolor_item_list").select(".teamcolor_item");

			for (Element param : data) {

				TeamcolorVo teamcolorVo = new TeamcolorVo();
				String level = param.select(".level").text().substring(0,1);

				String key = param.select(".btn_detail_link").attr("onclick");
				teamcolorVo.setKey(key.substring(key.indexOf("(")+1,key.indexOf(")"))+ "0" +level);
				teamcolorVo.setPersonnel(param.select(".num").text());

				key.substring(key.indexOf("(")+1,key.indexOf(")"));
				Document doc1 = Jsoup.connect(
						"https://fifaonline4.nexon.com/datacenter/teamcolor?strTeamColorCategory=%2Caffiliation%2C&strTeamColorType=%2Cclub%2C&strTeamColorName=")
						.get();
				Elements data1 = doc.select("div.teamcolor_item_list").select(".teamcolor_item");



			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String changeString(String data, int i) {
		if (!data.equals("")) {

			switch (data) {
			case "전체 능력치":
				System.out.println(data + "(detailStatAll) : " + i);
				break;
			case "속력":
				System.out.println(data + "(detailStat1) : " + i);
				break;
			case "가속력":
				System.out.println(data + "(detailStat2) : " + i);
				break;
			case "골 결정력":
				System.out.println(data + "(detailStat3) : " + i);
				break;
			case "슛 파워":
				System.out.println(data + "(detailStat4) : " + i);
				break;
			case "중거리 슛":
				System.out.println(data + "(detailStat5) : " + i);
				break;
			case "위치 선정":
				System.out.println(data + "(detailStat6) : " + i);
				break;
			case "발리슛":
				System.out.println(data + "(detailStat7) : " + i);
				break;
			case "페널티 킥":
				System.out.println(data + "(detailStat8) : " + i);
				break;
			case "짧은 패스":
				System.out.println(data + "(detailStat9) : " + i);
				break;
			case "시야":
				System.out.println(data + "(detailStat10) : " + i);
				break;
			case "크로스":
				System.out.println(data + "(detailStat11) : " + i);
				break;
			case "긴 패스":
				System.out.println(data + "(detailStat12) : " + i);
				break;
			case "프리킥":
				System.out.println(data + "(detailStat13) : " + i);
				break;
			case "커브":
				System.out.println(data + "(detailStat14) : " + i);
				break;
			case "드리블":
				System.out.println(data + "(detailStat15) : " + i);
				break;
			case "볼 컨트롤":
				System.out.println(data + "(detailStat16) : " + i);
				break;
			case "민첩성":
				System.out.println(data + "(detailStat17) : " + i);
				break;
			case "밸런스":
				System.out.println(data + "(detailStat18) : " + i);
				break;
			case "반응 속도":
				System.out.println(data + "(detailStat19) : " + i);
				break;
			case "대인 수비":
				System.out.println(data + "(detailStat20) : " + i);
				break;
			case "태클":
				System.out.println(data + "(detailStat21) : " + i);
				break;
			case "가로채기":
				System.out.println(data + "(detailStat22) : " + i);
				break;
			case "헤더":
				System.out.println(data + "(detailStat23) : " + i);
				break;
			case "슬라이딩 태클":
				System.out.println(data + "(detailStat24) : " + i);
				break;
			case "몸싸움":
				System.out.println(data + "(detailStat25) : " + i);
				break;
			case "스태미너":
				System.out.println(data + "(detailStat26) : " + i);
				break;
			case "적극성":
				System.out.println(data + "(detailStat27) : " + i);
				break;
			case "점프":
				System.out.println(data + "(detailStat28) : " + i);
				break;
			case "침착성":
				System.out.println(data + "(detailStat29) : " + i);
				break;
			case "GK 다이빙":
				System.out.println(data + "(detailStat30) : " + i);
				break;
			case "GK 핸들링":
				System.out.println(data + "(detailStat31) : " + i);
				break;
			case "GK 킥":
				System.out.println(data + "(detailStat32) : " + i);
				break;
			case "GK 반응속도":
				System.out.println(data + "(detailStat33) : " + i);
				break;
			case "GK 위치 선정":
				System.out.println(data + "(detailStat34) : " + i);
				break;
			default:
				break;
			}
		}

		return "";
	}

}
