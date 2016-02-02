package com.tianyl.pi.task;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianyl.pi.dao.StateDataDAO;
import com.tianyl.pi.model.StateData;
import com.tianyl.pi.util.DateUtil;
import com.tianyl.pi.util.RequestResult;
import com.tianyl.pi.util.YeeLinkUtil;

public class YeeLinkUploadTask implements Task {

	private static final Integer DEVICE_ID = 343680;

	private static final Integer CPU_SENSOR_ID = 383271;

	private static final Integer GPU_SENSOR_ID = 383272;

	@Override
	public void run() {
		List<StateData> stateDatas = StateDataDAO.queryUploadData();
		JSONArray cpuArr = new JSONArray();
		JSONArray gpuArr = new JSONArray();
		int maxId = -1;
		for (StateData sd : stateDatas) {
			maxId = sd.getId() > maxId ? sd.getId() : maxId;
			if (sd.getCpuTemp() != null) {
				JSONObject cpuObj = new JSONObject();
				cpuObj.put("timestamp", DateUtil.getYeeLinkDate(sd.getCtime().getTime()));
				cpuObj.put("value", sd.getCpuTemp() * 1d / 1000d);
				cpuArr.add(cpuObj);
			}
			if (sd.getGpuTemp() != null) {
				JSONObject gpuObj = new JSONObject();
				gpuObj.put("timestamp", DateUtil.getYeeLinkDate(sd.getCtime().getTime()));
				gpuObj.put("value", sd.getGpuTemp() * 1d / 1000d);
				gpuArr.add(gpuObj);
			}
		}
		boolean flag = true;
		if (cpuArr.size() > 0) {
			String url = "http://api.yeelink.net/v1.0/device/" + DEVICE_ID + "/sensor/" + CPU_SENSOR_ID + "/datapoints";
			RequestResult rr = YeeLinkUtil.post(url, cpuArr.size() == 1 ? cpuArr.getJSONObject(0).toJSONString() : cpuArr.toJSONString());
			flag = rr.isOk();
		}
		if (flag && gpuArr.size() > 0) {
			String url = "http://api.yeelink.net/v1.0/device/" + DEVICE_ID + "/sensor/" + GPU_SENSOR_ID + "/datapoints";
			RequestResult rr = YeeLinkUtil.post(url, gpuArr.size() == 1 ? gpuArr.getJSONObject(0).toJSONString() : gpuArr.toJSONString());
			flag = rr.isOk();
		}
		if (flag) {
			StateDataDAO.deleteByMaxId(maxId);
		}
	}

	@Override
	public int getPeriod() {
		return 20;
	}

}
