package com.victory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.victory.biz.model.PriceVo;

public class TestApplication {

	public static void main(String[] args) {

		Map<String, String> price = new HashMap<>();
		price.put("spid", "278158023");
		price.put("n1strong", "5");
		PriceVo priceVo = new PriceVo();

		Document doc;
		try {
			doc = Jsoup.connect("https://fifaonline4.nexon.com/datacenter/PlayerPriceGraph").data(price).post();

			System.out.println("현재가 : " + doc.select("div.add_info").select("Strong").text());

			Elements value = doc.select("div.price_tab").select("li");

			System.out.println("최저가 : " + value.get(0).select("strong").text());
			System.out.println("최고가 : " + value.get(1).select("strong").text());
			System.out.println("--------------------------------");

			Elements scripts = doc.select("script");
			String a = null;

			for (Element element : scripts) {
				if (element.data().contains("var json1")) {
					Pattern pattern = Pattern.compile(".*var json1 = ([^;]*);");
					Matcher matcher = pattern.matcher(element.data());
					if (matcher.find()) {
						a = matcher.group(1);
						break;
					} else {
						System.err.println("No match found!");
					}
					break;
				}
			}
			String strData = (a.substring(0, a.indexOf("}")+1));

			Gson gson = new Gson();
			PriceVo Json = gson.fromJson(strData, PriceVo.class);
			System.out.println("teamcolorVo : " + Json);

			System.out.println("--------------------------------");
			System.out.println("scripts : " + scripts.html());
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
