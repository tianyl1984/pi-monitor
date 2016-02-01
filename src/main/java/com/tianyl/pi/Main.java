package com.tianyl.pi;

import com.tianyl.pi.task.PiTempSaveTask;
import com.tianyl.pi.task.TaskManager;

public class Main {

	public static void main(String[] args) {
		TaskManager tm = new TaskManager();
		tm.addTask(new PiTempSaveTask());
		tm.start();
	}

}
