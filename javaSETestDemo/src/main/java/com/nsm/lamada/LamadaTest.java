package com.nsm.lamada;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LamadaTest {

	public static void test1() {

		// Random ran = new Random();

		// List<Student> stuList = new ArrayList<Student>();
		// long begin,end;
		//
		//
		// for (int i = 0; i < 100000; i++) {
		//
		// stuList.add(new
		// Student(ran.nextInt(100),UUID.randomUUID().toString()));
		//
		// }

		// System.out.println("before sort:");
		//
		// stuList.forEach((student)->System.out.println(student));
		//
		// stuList.sort((stu1,stu2)->stu1.getAge()-stu2.getAge());
		//
		// System.out.println("after sort:");
		// stuList.forEach((student)->System.out.println(student));

		// System.out.println("after filter:");
		// long number =
		// stuList.stream().parallel().filter((student)->student.getAge()<50).count();
		// System.out.println(number);
		// System.out.println("after limit");
		// stuList.stream().limit(2).forEach(System.out::println);

		List<Student> list1 = new ArrayList<>();
		List<Student> list2 = new ArrayList<>();
		list1.add(new Student(12, "a"));
		list1.add(new Student(13, "b"));

		list2.add(new Student(14, "c"));
		list2.add(new Student(12, "a"));

		List<Student> reduce = list1.stream().parallel()
				.filter((student) -> !list2.contains(student))
				.collect(Collectors.toList());
		
		
		reduce.forEach(System.out::println);
		
		list1.forEach(System.out::println);
		List<String> list3 = new ArrayList<>();
		list3.stream();
	}
	
	public void testParallelStream(){
		List<Map<String,String>> list = new ArrayList<>();
		String str = "9";
		Map<String, String> map1;
		Random ran = new Random();
		for(int i=0;i<20000000;++i){
			map1 = new HashMap<>();
			map1.put("test", Integer.toString(ran.nextInt(10000)));
			list.add(map1);
		}
		
		
		System.out.println("处理前：");
		//list.forEach((map)->{map.values().forEach(System.out::println);});
		System.out.println(list.size()+"个");
		long before = System.currentTimeMillis();
		List<Map<String, String>> newList = list.stream()
				.filter((map)->map.get("test").equals(str))
				.collect(Collectors.toList());
		long after = System.currentTimeMillis();
		
		System.out.println("处理后：");
		//newList.forEach((map)->{map.values().forEach(System.out::println);});
		System.out.println(newList.size()+"个");
		System.out.println("用时"+(after - before));
	}
	
	public void testParallelStream2(){
		List<Integer> list = new ArrayList<>();
		Random ran = new Random();
		IntStream.range(0, 200000000).forEach(value->list.add(value));
		System.out.println("处理前：");
		System.out.println(list.size()+"个");
//		long before = System.currentTimeMillis();
//		List<Integer> list2 = list.parallelStream().filter((value)->value==999).collect(Collectors.toList());
//        long after = System.currentTimeMillis();
//		System.out.println("处理后：");
//		System.out.println(list2.size()+"个");
//		System.out.println("用时"+(after - before));
	}
	
	public static void test2(){
		List<Student> list = new ArrayList<>();
		list.add(new Student(1, "1"));
		list.add(new Student(2, "2"));
		List<Student> list2 = list.stream().map(student->new Student(3,"3")).collect(Collectors.toList());
		list2.forEach(System.out::println);
	}
	
	public static void test3(){
		List<Student> stuList = Arrays.asList(new Student(18,"小明"),new Student(19,"小红"));
		List<Integer> collect = stuList.stream().map(student->student.getAge()).collect(Collectors.toList());
		collect.forEach(System.out::println);
	}
	
	public static void test4(){
		List<String> lists = Arrays.asList("long","may","the","sun","shine");
		List<String> collect = lists.stream().map(str->str.split("")).flatMap(array->Arrays.stream(array)).distinct().collect(Collectors.toList());
		collect.forEach(System.out::println);
	}
	
	public static void test5(){
		Consumer<String> c1 = new TestClass()::testMethod1;
		c1.accept("testMethod1");
		
		Consumer<String> c2 = TestClass::testMethod2;
		c2.accept("testMethod2");
		
		Function<String, TestClass> f = TestClass::new;
		TestClass testClass = f.apply("Long may the sun shine!");
		System.out.println(testClass);
	}
	
	public static void main(String[] args) {
		//new LamadaTest().testParallelStream2();
		//test2();
		//test3();
		test4();
		//test5();
	}

}
