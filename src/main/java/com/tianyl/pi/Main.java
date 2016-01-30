package com.tianyl.pi;

import com.alibaba.fastjson.JSONObject;

public class Main {

	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.put("result", "OK");
		System.out.println(json.getString("result"));
	}

}
