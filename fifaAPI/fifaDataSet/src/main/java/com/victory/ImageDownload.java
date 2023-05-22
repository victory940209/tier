package com.victory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class ImageDownload {

	public static void main(String[] args) throws Exception{
		ImageDownload id = new ImageDownload();

		String strUrl = "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/playersAction/p242020801.png"; // 불러올 URL

		RestTemplate rt = new RestTemplate();

		ResponseEntity<String> re =  rt.getForEntity("https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/playersAction/p216020801.png", String.class);
		System.out.println(re.getStatusCode());

		System.out.println("이미지 없음");

//		try {
//			id.saveImage(strUrl);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private void saveImage(String strUrl) throws IOException {
		URL url = null;
		InputStream in = null;
		OutputStream out = null;

		try {
			url = new URL(strUrl);
			in = url.openStream();
			out = new FileOutputStream("D:/git/tier/reactView/public/player/p" + "242020801" + ".png");
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
