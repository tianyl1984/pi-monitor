package com.tianyl.pi.task;

import java.sql.Timestamp;

import com.tianyl.pi.dao.StateDataDAO;
import com.tianyl.pi.model.StateData;
import com.tianyl.pi.util.PiUtil;

public class PiTempSaveTask implements Task {

	@Override
	public void run() {
		Integer cpuTemp = PiUtil.getCpuTemp();
		Integer gpuTemp = PiUtil.getGpuTemp();
		StateData sd = new StateData();
		sd.setCpuTemp(cpuTemp);
		sd.setGpuTemp(gpuTemp);
		sd.setUpload(StateData.UPLOAD_NO);
		sd.setCtime(new Timestamp(System.currentTimeMillis()));
		StateDataDAO.save(sd);
	}

	@Override
	public int getPeriod() {
		return 60;
	}

	@Override
	public String getName() {
		return "TempSaveTask";
	}

}
