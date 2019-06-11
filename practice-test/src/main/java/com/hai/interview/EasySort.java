package com.hai.interview;

import java.util.Arrays;

/**
 * 简单的排序
 * 
 * @author Administrator
 *
 */
public class EasySort {
	public static void main(String[] args) {
		EasySort e = new EasySort();
		e.sortArrays();
		e.sortBubble();

	}

	// 简单数组排序前一位比后一位，大则换位置
	public void sortArrays() {
		int[] number = new int[] { 1, 200, 30, 20, 100, 9, 20, 10, 50 };
		int n = 0;
		for (int i = 0; i < number.length; i++) {
			for (int j = i + 1; j < number.length; j++) {
				if (number[i] > number[j]) {
					System.out.println(n++);
					int temp = number[j];
					number[j] = number[i];
					number[i] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(number));
	}

	// 冒泡排序
	public void sortBubble() {
		int[] arr = { 12, 34, 31, 56, 100, 56, 78 ,90};
		int n = 1;
		for (int i = 0; i < arr.length - 1; i++) {
			// -1为了防止溢出
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					System.out.println(n++);
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
	}


	// 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，
	// 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
	public boolean Find(int target, int[][] array) {
		int rows = array.length;
		int cols = array[0].length;
		int i = rows - 1, j = 0;
		while (i >= 0 && j < cols) {
			if (target < array[i][j])
				i--;
			else if (target > array[i][j])
				j++;
			else
				return true;
		}
		return false;
	}

}
