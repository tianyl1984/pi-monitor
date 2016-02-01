package com.tianyl.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TaskManager {

	private List<Task> tasks = new ArrayList<Task>();

	public void addTask(Task task) {
		tasks.add(task);
	}

	public void start() {
		for (final Task task : tasks) {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					task.run();
				}
			}, 1000, 1000 * task.getPeriod());
		}
	}

}
