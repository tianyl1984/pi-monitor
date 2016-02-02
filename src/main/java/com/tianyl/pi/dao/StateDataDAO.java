package com.tianyl.pi.dao;

import java.util.List;

import com.tianyl.pi.model.StateData;
import com.tianyl.pi.util.sql.JdbcUtil;

public class StateDataDAO {

	public static void save(StateData sd) {
		String sql = "insert into state_data(cpu_temp,gpu_temp,ctime,upload) values(?,?,?,?)";
		JdbcUtil.save(sql, sd.getCpuTemp(), sd.getGpuTemp(), sd.getCtime(), sd.getUpload());
	}

	public static List<StateData> queryUploadData() {
		String sql = "select * from state_data order by id limit 50";
		return JdbcUtil.query(sql, StateDataMapper.getInstance());
	}

	public static void deleteByMaxId(Integer maxId) {
		String sql = "delete from state_data where id <= ? ";
		JdbcUtil.update(sql, maxId);
	}

}
