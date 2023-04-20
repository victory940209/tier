package com.victory;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class teamcolorApplication {

	public static void main(String[] args) {

		try {

//			Document doc = Jsoup
//					.connect("https://fifaonline4.inven.co.kr/dataninfo/teamcolor2/?searchword=&tcolor2type%5B%5D=11")
//					.get();
//			Elements data = doc.select("div.board_teamcolor").select(".table").select("tbody").select("tr");
//
//			for (Element param : data) {
//				// key
//				System.out.println("key : " + param.attr("data-code"));
//				// type
//				System.out.println("type : club ");
//				// 이름
//				String name = param.select(".name").text();
//				System.out.println("name : " + name.substring(6, name.length()));
//				// 인원
//				// System.out.println("personnel : " + param.attr("data-tcolor2type"));
//				// 레벨
//				System.out.println("level : " + param.attr("data-tcolor2level"));
//				// 능력치
//				Elements detailStat = param.select(".text_center").select("p");
//				for (Element pa : detailStat) {
//					String stat = pa.text();
//
//					changeString(stat.subSequence(0, stat.indexOf("+") - 1).toString(),
//							Integer.valueOf(stat.subSequence(stat.indexOf("+") + 1, stat.length()).toString()));
//				}
//				System.out.println("--------------------------------------------------");
//			}

			Document docff = Jsoup.connect(
					"https://fifaonline4.nexon.com/datacenter/teamcolor?strTeamColorCategory=%2Caffiliation%2C&strTeamColorType=%2Cclub%2C&strTeamColorName=")
					.get();
			Elements data1 = docff.select("div.teamcolor_item_list").select(".teamcolor_item");


//			for (Element param : data1) {
//
//				//key matching
//
//				System.out.println("onclick : " + param.select(".btn_detail_link").attr("onclick"));
//				System.out.println("personnel : " + param.select(".num").text());
//			}

			Element param = data1.get(0);
			String key = param.select(".btn_detail_link").attr("onclick");

			System.out.println("key : " + key.substring(key.indexOf("(")+1,key.indexOf(")")));
			System.out.println("personnel : " + param.select(".num").text());
			// Gson gson = new Gson();
			// String Json = gson.toJson(playerVo, PlayerVo.class).toString();
			// System.out.println("result : " + Json);

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
