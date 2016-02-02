package com.tianyl.pi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tianyl.pi.model.StateData;
import com.tianyl.pi.util.sql.JdbcUtil;
import com.tianyl.pi.util.sql.RowMapper;

public class StateDataMapper implements RowMapper<StateData> {

	@Override
	public StateData mapRow(ResultSet rs, int rowNum) throws SQLException {
		StateData sd = new StateData();
		sd.setCpuTemp(JdbcUtil.getInteger(rs, "cpu_temp"));
		sd.setCtime(rs.getTimestamp("ctime"));
		sd.setGpuTemp(JdbcUtil.getInteger(rs, "gpu_temp"));
		sd.setId(rs.getInt("id"));
		return sd;
	}

	private static final StateDataMapper INSTANCE = new StateDataMapper();

	public static StateDataMapper getInstance() {
		return INSTANCE;
	}

}
