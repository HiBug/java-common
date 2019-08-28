package com.xin.stopwatch;

import org.springframework.util.StopWatch;

/**
 * @author three
 * @since 2019-08-28 10:06
 * <p>
 *
 * </p>
 */
public class StopWatchTest {
	private static void test() throws InterruptedException {
		StopWatch sw = new StopWatch();

		sw.start("起床");
		Thread.sleep(1000);
		sw.stop();

		sw.start("洗漱");
		Thread.sleep(2000);
		sw.stop();

		sw.start("锁门");
		Thread.sleep(500);
		sw.stop();

		System.out.println(sw.prettyPrint());
		System.out.println(sw.getTotalTimeMillis());
		System.out.println(sw.getLastTaskName());
		System.out.println(sw.getLastTaskInfo());
		System.out.println(sw.getTaskCount());
	}

	public static void main(String[] args) throws Exception {
		test();
	}

}
