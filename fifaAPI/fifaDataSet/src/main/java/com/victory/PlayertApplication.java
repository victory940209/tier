package com.victory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.victory.biz.model.PlayerVo;

public class PlayertApplication {

	public static void main(String[] args) {

		try {

			Document doc = Jsoup.connect("https://fifaonline4.nexon.com/DataCenter/PlayerInfo?spid=101000240&n1Strong=1").get();

			PlayerVo playerVo = new PlayerVo();
			playerVo.setId("101190043");
			playerVo.setName(doc.select("div.name").get(0).text());
			playerVo.setSeason(doc.select("div.season_match").select(".season").select("[checked]").attr("id"));
			playerVo.setPaySide(changeString(doc.select("div.pay_side").text()));

			Elements mainPosition = doc.select("div.info_ab").select(".position");
			int i = 1;
			List<String> MPlist = new ArrayList<>();
			List<Integer> MPvlist = new ArrayList<>();
			for (Element element : mainPosition) {
				MPlist.add(element.select(".position").select(".txt").text());

			}
			playerVo.setMainPosition(MPlist);


			playerVo.setBirth(doc.select("div.info_etc").select(".birth").text());
			playerVo.setHeight(changeString(doc.select("div.info_etc").select(".height").text().replace("cm", "")));
			playerVo.setWeight(changeString(doc.select("div.info_etc").select(".weight").text().replace("kg", "")));
			playerVo.setPhysical(doc.select("div.info_etc").select(".physical").text());
			playerVo.setSkill(doc.select("div.info_etc").select(".skill").text());
			String result = doc.select("div.info_etc").select(".foot").text();

			playerVo.setLFoot(changeString(result.substring(1,2)));
			playerVo.setRFoot(changeString(result.substring(6,7)));

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
					playerVo.setDetailStat1((Integer.valueOf(element.select(".value").text()) + 3) );
				if (i == 2)
					playerVo.setDetailStat2((Integer.valueOf(element.select(".value").text()) + 3) );
				if (i == 3)
					playerVo.setDetailStat3((Integer.valueOf(element.select(".value").text()) + 3) );
				if (i == 4)
					playerVo.setDetailStat4((Integer.valueOf(element.select(".value").text()) + 3) );
				if (i == 5)
					playerVo.setDetailStat5((Integer.valueOf(element.select(".value").text()) + 3) );
				if (i == 6)
					playerVo.setDetailStat6((Integer.valueOf(element.select(".value").text()) + 3) );
				if (i == 7)
					playerVo.setDetailStat7((Integer.valueOf(element.select(".value").text()) + 3) );
				if (i == 8)
					playerVo.setDetailStat8((Integer.valueOf(element.select(".value").text()) + 3) );
				if (i == 9)
					playerVo.setDetailStat9((Integer.valueOf(element.select(".value").text()) + 3) );
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

			Gson gson = new Gson();
			String Json = gson.toJson(playerVo, PlayerVo.class).toString();

			System.out.println("result : " + Json);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static int changeString(String data) {

		if(!data.equals("")) {
			return Integer.valueOf(data);
		}

		return 0;
	}

}
