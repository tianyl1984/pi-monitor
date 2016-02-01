package com.tianyl.pi.util;

public class PiUtil {

	public static Integer getGpuTemp() {
		String retStr = exe("/opt/vc/bin/vcgencmd measure_temp");
		if (StringUtil.isNotBlank(retStr)) {
			return Double.valueOf(Double.valueOf(retStr.replace("temp=", "").replace("'C", "").trim()) * 1000).intValue();
		}
		return null;
	}

	public static Integer getCpuTemp() {
		String retStr = exe("cat /sys/class/thermal/thermal_zone0/temp");
		if (StringUtil.isNotBlank(retStr)) {
			return Integer.valueOf(retStr.trim());
		}
		return null;
	}

	private static String exe(String cmd) {
		try {
			Process ps = Runtime.getRuntime().exec(cmd);
			int ret = ps.waitFor();
			if (ret == 0) {
				byte[] bs = IOUtils.toByteArray(ps.getInputStream());
				return new String(bs);
			} else {
				byte[] bs = IOUtils.toByteArray(ps.getErrorStream());
				System.out.println("error:" + new String(bs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
