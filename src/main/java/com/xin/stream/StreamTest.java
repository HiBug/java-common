package com.xin.stream;

import com.google.common.collect.Lists;
import com.xin.util.Printer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author three
 * @since 2019/4/12 17:26
 * <p>
 *
 * </p>
 */
public class StreamTest {
	public static void main(String[] args) {
		List<String> array = Lists.newArrayList("123", "124", "125");
		Printer.jsonPrint(
				array.stream()
						.sorted(Comparator.comparing(String::toString))
						.collect(Collectors.toList()));

	}
}
