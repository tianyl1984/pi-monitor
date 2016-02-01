package com.tianyl.pi.dao;

import com.tianyl.pi.model.StateData;
import com.tianyl.pi.util.sql.JdbcUtil;

public class StateDataDAO {

	public static void save(StateData sd) {
		String sql = "insert into state_data(cpu_temp,gpu_temp,ctime,upload) values(?,?,?,?)";
		JdbcUtil.save(sql, sd.getCpuTemp(), sd.getGpuTemp(), sd.getCtime(), sd.getUpload());
	}

}
