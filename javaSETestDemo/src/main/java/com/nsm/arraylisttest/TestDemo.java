package com.nsm.arraylisttest;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.nsm.lamada.People;
import com.nsm.lamada.Student;

/** 
 * @author nasm 
 * @date 创建时间：2019年2月13日 下午2:04:21 
 * @version 1.0  
 */
public class TestDemo {

	public void quickSort(int[] array,int begin,int end){
		if (begin < end){
			int key = sort(array, begin, end);
			quickSort(array, begin, key-1);
			quickSort(array, key+1, end);
		}
	}

	public int sort(int[] array,int begin,int end){
		int i = begin;
		int j = end;
		int key = array[i];
		while(i<j){
			while (i<j && array[j]>key){
				j--;
			}
			if (i<j){
				array[i] = array[j];
				i++;
			}
			while (i<j && array[i]<key){
				i++;
			}
			if (i<j){
				array[j] = array[i];
				j--;
			}
		}
		array[i] = key;
		return i;
	}
	
	public static void main(String[] args) throws Exception {
		List<String> list = new ArrayList<>();
		list.add("1,2,3");
		list.add("4,5,6");
		List<String> stringList = list.stream().map((str) -> str.split(",")).flatMap((array) -> {
			return Arrays.stream(array);
		}).collect(Collectors.toList());

		stringList.forEach(System.out::println);

	}
}
