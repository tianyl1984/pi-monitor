package com.tianyl.pi.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.tianyl.pi.log.LogManager;

public class YeeLinkUtil {

	public static RequestResult post(String url, String body) {
		RequestResult rr = new RequestResult();
		try {
			HttpURLConnection conn = (HttpURLConnection) (new URL(checkUrl(url)).openConnection());
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(30 * 1000);
			conn.setReadTimeout(30 * 1000);
			conn.setUseCaches(false);
			// 仅对当前请求自动重定向
			conn.setInstanceFollowRedirects(true);
			// header 设置编码
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("U-ApiKey", getKey());
			// 连接
			conn.connect();
			writeBody(conn, body);
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				rr.setOk(false);
				rr.setResultBytes(IOUtils.toByteArray(conn.getErrorStream()));
				LogManager.log("error response code:" + conn.getResponseCode());
				LogManager.log("error msg : ");
				LogManager.log(rr.getResultStr());
			} else {
				byte[] bs = IOUtils.toByteArray(conn.getInputStream());
				rr.setOk(true);
				rr.setResultBytes(bs);
			}
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			LogManager.log(e);
			rr.setOk(false);
		}
		return rr;
	}

	private static String getKey() {
		String key = FileUtil.read(new File("/home/pi/pidata/yeelink.key"));
		if (StringUtil.isBlank(key)) {
			throw new RuntimeException("获取 U-ApiKey错误");
		}
		return key;
	}

	private static void writeBody(HttpURLConnection conn, String body) throws IOException {
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.writeBytes(body);
		out.flush();
		out.close();
	}

	private static String checkUrl(String url) {
		String result = url;
		if (url.startsWith("http://")) {
			url = url.replaceFirst("http://", "");
			if (url.contains("//")) {
				url = url.replaceAll("//", "/");
			}
			result = "http://" + url;
		}
		return result;
	}

}
