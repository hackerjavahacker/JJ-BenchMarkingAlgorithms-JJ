package org.example;

// Java implementation of QuickSort
import java.io.*;

class QuickSort {

	private static long count;
	private static long time;
	private static long previousTime;

	public QuickSort(int arr[]) {
		count = 0;
		time = 0;
		previousTime = System.nanoTime();
		quickSort(arr, 0, arr.length - 1);
	}

	// A utility function to swap two elements
	static void swap(int[] arr, int i, int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		hakimOperation(4);
	}

	/* This function takes last element as pivot, places
	   the pivot element at its correct position in sorted
	   array, and places all smaller (smaller than pivot)
	   to left of pivot and all greater elements to right
	   of pivot */
	static int partition(int[] arr, int low, int high)
	{

		// pivot
		int pivot = arr[high];

		// Index of smaller element and
		// indicates the right position
		// of pivot found so far
		int i = (low - 1);
		hakimOperation(4);

		for (int j = low; j <= high - 1; j++) {

			// If current element is smaller
			// than the pivot
			if (arr[j] < pivot) {

				// Increment index of
				// smaller element
				i++;
				hakimOperation(3);
				swap(arr, i, j);
			}
		}
		hakimOperation(1 + (3 * (high - low + 1)) + (2 * (high - low)));
		swap(arr, i + 1, high);
		hakimOperation(2);
		return (i + 1);
	}

	/* The main function that implements QuickSort
			  arr[] --> Array to be sorted,
			  low --> Starting index,
			  high --> Ending index
	 */
	static void quickSort(int[] arr, int low, int high)
	{
		hakimOperation(1);
		if (low < high) {

			// pi is partitioning index, arr[p]
			// is now at right place
			int pi = partition(arr, low, high);
			hakimOperation(2);

			// Separately sort elements before
			// partition and after partition
			quickSort(arr, low, pi - 1);

			quickSort(arr, pi + 1, high);
		}
	}

	public long getCount() {
		return count;
	}

	public long getTime() {
		return time;
	}

	public static void hakimOperation(int additionalCount) {
		time += System.nanoTime() - previousTime;
		count += additionalCount;
		previousTime = System.nanoTime();
	}

}
