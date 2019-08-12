package com.nsm.arraylisttest;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nsm.lamada.People;
import com.nsm.lamada.Student;

/** 
 * @author nasm 
 * @date 创建时间：2019年2月13日 下午2:04:21 
 * @version 1.0  
 */
public class TestDemo {
	
	public static<T> void father2Child(T father,T child){
		
	}
	
	public static void main(String[] args) throws Exception {
		//List<Integer> list = new ArrayList<Integer>();
//		Collections.addAll(list, new Integer[]{1,2,3,3,4,5});
//		Iterator<Integer> iterator = list.iterator();
//		while(iterator.hasNext()){
//			if(iterator.next()==3){
//				iterator.remove();
//				break;
//			}
//		}
//		list.forEach(System.out::println);
//		Map<Integer,Object> map = new HashMap<Integer,Object>();
//		map.put(1, 1);
//		map.put(2, 2);
//		System.out.println(map.get(3));
		People p = new People();
		p.setSex("");
		Student stu = new Student();
		Class<People> clazz = People.class;
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			field.set(stu, field.get(p));
		}
//		Student stu = (Student)p2;
		String[] split = stu.getSex().split(",");
		List<String> list = new ArrayList<>();
		Collections.addAll(list, split);
		list.forEach((str)->{
			System.out.println(str);
		});
		System.out.println(list == null);
	}
}
