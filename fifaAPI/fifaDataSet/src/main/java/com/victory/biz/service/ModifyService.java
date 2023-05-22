package com.victory.biz.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.victory.ImageDownload;
import com.victory.biz.model.PlayerVo;
import com.victory.biz.model.SeasonVo;
import com.victory.biz.model.SpidVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ModifyService {

	@Autowired
	private MongoDBService mongoDBService;


	public void modifyPlayer() {
		List<SpidVo>  spidlist = mongoDBService.selectSpidAll();

//		for(SpidVo spiddata: spidlist) {
			SpidVo spiddata = spidlist.get(0);
			PlayerVo playerData = mongoDBService.selPlayer(spiddata.getKey()+"");
			log.info("PlayerData : " + playerData);
			String id = playerData.getId();


			String pid = id.substring(3,id.length());
			id = id.substring(0,3);
			log.info("#### : " + id);
			//season set
			SeasonVo seasonVo = mongoDBService.selSeanson(Integer.valueOf(id));

			log.info("seasonVo : " + seasonVo);
			playerData.setSeason(seasonVo.getSeasonId()+"");

			//image set
			try {
			String strUrl = "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/playersAction/p" + playerData.getId() +".png"; // 불러올 URL
			RestTemplate rt = new RestTemplate();
			ResponseEntity<String> re =  rt.getForEntity(strUrl, String.class);

			saveImage(strUrl, playerData.getId());
			playerData.setImg("./prayer/p" + playerData.getId() + ".png");

			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("이미지 없음");
				String strUrl = "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/players/p" + pid + ".png"; // 불러올 URL
				try {
					saveImage(strUrl, pid);
					playerData.setImg("./prayer/p" + pid + ".png");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			log.info("playerData : " + playerData);
//			mongoDBService.savePlayerSeason(playerData);

//		}

	}

	private void saveImage(String strUrl, String id) throws IOException {
		URL url = null;
		InputStream in = null;
		OutputStream out = null;

		try {
			url = new URL(strUrl);
			in = url.openStream();
			out = new FileOutputStream("D:/git/tier/reactView/public/player/p" + id + ".png");
			while (true) {
				// 이미지를 읽어온다.
				int data = in.read();
				if (data == -1) {
					break;
				}
				// 이미지를 쓴다.
				out.write(data);
			}
			in.close();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}

		}
	}
}
