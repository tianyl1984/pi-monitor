package com.tianyl.pi;

import com.tianyl.pi.util.PiUtil;

public class Main {

	public static void main(String[] args) {
		System.out.println(PiUtil.getCpuTemp());
		System.out.println(PiUtil.getGpuTemp());
	}

}
