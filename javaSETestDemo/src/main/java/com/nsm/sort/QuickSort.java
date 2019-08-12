package com.nsm.sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
	
	public static void sort(int[] array){
		sort(0, array.length-1, array);
	}
	
	public static void sort(int begin,int end,int[] array){
		if(!(begin<end)){
			return;
		}
		int index = realSort(begin, end, array);
		sort(begin, index-1, array);
		sort(index+1, end, array);
	}
	
	private static int realSort(int begin,int end,int[] array){
			int i = begin;
			int j = end;
			int key = array[i];
			while(i<j){
				while(i<j&&key<array[j]){
					j--;
				}
				if(i<j){
					array[i]=array[j];
					i++;
				}
				while(i<j&&key>array[i]){
					i++;
				}
				if(i<j){
					array[j]=array[i];
					j--;
				}
			}
			array[i] = key;
			return i;
	}
	
	public static void main(String[] args) {
		Random ran = new Random();
		int[] array = new int[5];
		for (int i = 0; i < array.length; i++) {
			array[i] = ran.nextInt(6);
		}
		System.out.println(Arrays.toString(array));
		sort(array);
		System.out.println(Arrays.toString(array));
	}

}
