package com.victory;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestApplication {

	public static void main(String[] args) {

		try {

			Document doc = Jsoup.connect("https://fifaonline4.nexon.com/DataCenter/PlayerInfo?spid=278165153").get();

			System.out.println("이름 : " + doc.select("div.name").get(0).text());
			System.out.println("급여 : " + doc.select("div.pay_side").text());


			Elements mainPosition = doc.select("div.info_ab").select("position");
			int i = 0;
			for(Element element: mainPosition) {

				System.out.println("주포" + i + " : " + element.select(".position").select(".txt").text());
				System.out.println("능력치 : " + element.select(".value").text());
			}

			System.out.println("나이 : " + doc.select("div.info_etc").select(".birth").text());
			System.out.println("키 : " + doc.select("div.info_etc").select(".height").text());
			System.out.println("몸무게 : " + doc.select("div.info_etc").select(".weight").text());
			System.out.println("피지컬 : " + doc.select("div.info_etc").select(".physical").text());
			System.out.println("개인기 : " + doc.select("div.info_etc").select(".skill").text());
			System.out.println("발 : " + doc.select("div.info_etc").select(".foot").text());
			System.out.println("명성 : " + doc.select("div.info_etc").select(".season").text());


			System.out.println("국가 : " + doc.select("div.info_team").select(".nation").select(".txt").text());
			System.out.print("특성 : ");

			Elements skill_wrap = doc.select("div.skill_wrap").select("span");

			for(Element element: skill_wrap) {
				System.out.println(" " + element.text() + ",");
			}

			Elements position_ovr = doc.select("div.ovr_set").select(".position");
			for(Element element: position_ovr) {
				System.out.println("포지션 : " + element.removeClass("position").removeClass("value").className() + " , 스탯 : " + element.text());
			}
			System.out.println("--- 대표 스탯 ---");
			Elements content_middle = doc.select("div.content_middle").select(".ab");
			for(Element element: content_middle) {
				System.out.println(element.select(".txt").text() + " : " + element.select(".value").text());
			}

			System.out.println("--- 세부 스탯 ---");
			Elements content_bottom = doc.select("div.content_bottom").select(".ab");
			for(Element element: content_bottom) {
				System.out.println(element.select(".txt").text() + " : " + element.select(".value").text());
			}

			System.out.println("--- 이력 ---");
			Elements club = doc.select("div.info_ab").select("position");
			for(Element element: club) {

				System.out.println("주포" + i + " : " + element.select(".position").select(".txt").text());
				System.out.println("능력치 : " + element.select(".value").text());
			}

			//position



//			// 코드
//			System.out.println("코드 " + element.getElementsByAttribute("data-playercode").attr("data-playercode"));
//			// 이름
//			System.out.println("이름 " + element.getElementsByAttribute("data-playercode").attr("data-playershortname"));
//
//			// 포지션
//			element.getElementsByClass("tooltip_position").get(0);
//
//			// 적극성
//			System.out.println("적극성 up " + element.getElementsByClass("up"));
//			System.out.println("적극성 down " + element.getElementsByClass("down"));
//
//			Elements state = element.getElementsByClass("state").get(0).children();
//
//			for (Element child1 : state) {
//				System.out.println("state child : " + child1.html());
//			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
