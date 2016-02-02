package com.tianyl.pi.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getYeeLinkDate(long time) {
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		return sdf.format(date);
	}

}
