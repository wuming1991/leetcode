package com.wuming;

import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;
import static java.util.stream.Collectors.toList;

import com.wuming.pattern.prototype.Entity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class App {
	
	public static void main(String[] args) {
		String[] words = {"He,l,lo", "Wor,l,d"};
		List<String[]> collect = Arrays.stream(words)
			.map(word -> word.split(""))
			.distinct()
			.collect(toList());
		List<String> collect1 = Arrays.stream(words)
			.flatMap(s -> Arrays.stream(s.split(",")))
			.distinct()
			.collect(toList());
		System.out.println("collect1" + collect1);
		System.out.println(collect.get(1));
		String ss = "Hello";
		String[] split = ss.split("");
		Arrays.stream(split).forEach(System.out::println);
		ReentrantLock lock = new ReentrantLock();
		ArrayList<Integer> integers = new ArrayList<>(1000);
		IntStream.range(0, 1000).parallel().forEach(i -> {
			lock.lock();
			try {
				integers.add(i);
			} finally {
				lock.unlock();
			}
		});
		System.out.println(integers.size());
		if (integers.size() != 1000) {
			System.out.println(integers);
			for (int i = 0; i < integers.size(); i++) {
				if (!integers.contains(i)) {
					System.out.println(i);
				}
			}
		}
		
	}
	
	public static Boolean overZero1(int i, Function<Integer, Boolean> function) {
		Boolean apply = function.apply(i);
		return apply;
	}
	
	public static <T> Boolean check(T t, Predicate<T> predicate) {
		return predicate.test(t);
	}
	
	public static Boolean overZero(int i, Predicate<Integer> predicate) {
		boolean test = predicate.test(i);
		return test;
	}
	
	public String sayHello() {
		return "hello";
	}
}

