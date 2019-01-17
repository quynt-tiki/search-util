package com.pheu.common;

public class Helper {
	private Helper() {
	}

	public static void sleep(long milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch (InterruptedException localInterruptedException) {
		}
	}
}
