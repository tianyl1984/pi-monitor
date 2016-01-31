package com.tianyl.pi.util;

import java.io.UnsupportedEncodingException;

public class RequestResult {

	private boolean isOk;

	private String resultStr;

	private byte[] resultBytes;

	public RequestResult() {

	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public String getResultStr() {
		try {
			resultStr = new String(resultBytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public byte[] getResultBytes() {
		return resultBytes;
	}

	public void setResultBytes(byte[] resultBytes) {
		this.resultBytes = resultBytes;
	}
}
