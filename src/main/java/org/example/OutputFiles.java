package org.example;

import java.util.Random;
import java.lang.*;
import java.io.*;

public class OutputFiles {

	private Random rand = new Random();
	private void initializer(int arr[]) {
		for (int i = 0; i < arr.length; i ++) {
			arr[i] = (int) (1000 * rand.nextDouble());
		}
	}

	private boolean checkSort(int arr[]) {
		for (int i = 0; i < arr.length - 1; i ++) {
			if (arr[i] > arr[i + 1]) {
				return false;
			}
		}

		return true;
	}

	private String getSortResult(Boolean sortType) {
		String strResult = "{ Hakim Khan\n";
		int arr[];
		long count, time;

		for (int i = 0; i < 12; i ++) {
			arr = new int[(100 * (i + 1))];
			strResult += (100 * (i + 1)) + " | ";

			for (int j = 0; j < 40; j ++) {
				initializer(arr);
				if (sortType) {
					MergeSort mergeSort = new MergeSort(arr);
					count = mergeSort.getCount();
					time = mergeSort.getTime();
				} else {
					QuickSort quickSort = new QuickSort(arr);
					count = quickSort.getCount();
					time = quickSort.getTime();
				}
				strResult += "[" + count + ", " + time + "] ";

				if (!checkSort(arr)) {
					throw new RuntimeException("The array has not been sorted correctly!");
				}
			}
			strResult += "\n";
		}

//		System.out.println(strResult);
		strResult += "}";

		return strResult;
	}

	public static void main(String[] args) throws IOException {
		OutputFiles outputFiles = new OutputFiles();
		FileOutputStream mergeSortResult = null;
		FileOutputStream quickSortResult = null;
		try {
			mergeSortResult = new FileOutputStream("mergeSortResult.txt");
			quickSortResult = new FileOutputStream("quickSortResult.txt");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		byte[] mergeResult = outputFiles.getSortResult(true).getBytes();
		byte[] quickResult = outputFiles.getSortResult(false).getBytes();
		mergeSortResult.write(mergeResult);
		quickSortResult.write(quickResult);

		System.out.println("Hello world!");
	}
}