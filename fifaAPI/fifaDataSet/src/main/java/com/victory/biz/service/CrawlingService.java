package com.victory.biz.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CrawlingService {

	 private WebDriver driver;

	 public void getCrawling(String url) {

		   try {
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.select(".db_player_info");
			for( Element element : elements ) {
			    log.info("data : " + element.data());

			    log.info("html : " + element.html());

			    log.info("attributes" + element.attributes());
			} // for end
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public void getselCrawling(String url) {

		// Set up Chrome driver
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-gpu"); // gpu 비활성화
		options.addArguments("--blink-settings=imagesEnabled=false"); // 이미지 다운 안받음

		driver = new ChromeDriver(options);

		try {
			getDataList(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.close(); // 탭 닫기
		driver.quit(); // 브라우저 닫기

	}

	private List<String> getDataList(String url) throws InterruptedException {
		  List<String> list = new ArrayList<>();

	        driver.get(url);    //브라우저에서 url로 이동한다.
	        List<WebElement> elements = driver.findElements(By.cssSelector(".info clearfix"));
	        for (WebElement element : elements) {
	            log.info("----------------------------");
	            log.info("element : " + element);	//⭐
	            log.info("----------------------------");
	        }

	        return list;
	}

}
