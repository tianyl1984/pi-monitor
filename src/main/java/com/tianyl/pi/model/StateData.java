package com.tianyl.pi.model;

import java.sql.Timestamp;

public class StateData {

	private Integer id;

	private Integer cpuTemp;

	private Integer gpuTemp;

	private Timestamp ctime;

	private Integer upload;

	public static final Integer UPLOAD_NO = 0;

	public static final Integer UPLOAD_YES = 1;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCpuTemp() {
		return cpuTemp;
	}

	public void setCpuTemp(Integer cpuTemp) {
		this.cpuTemp = cpuTemp;
	}

	public Integer getGpuTemp() {
		return gpuTemp;
	}

	public void setGpuTemp(Integer gpuTemp) {
		this.gpuTemp = gpuTemp;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Integer getUpload() {
		return upload;
	}

	public void setUpload(Integer upload) {
		this.upload = upload;
	}

}
