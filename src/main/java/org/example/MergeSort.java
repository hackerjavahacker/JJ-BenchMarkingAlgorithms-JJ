package org.example;


/* Java program for Merge Sort */
class MergeSort {

	private int count;
	private long time;
	private long previousMomentTime;

	// Merges two subarrays of arr[].
	// First subarray is arr[l..m]
	// Second subarray is arr[m+1..r]

	public MergeSort(int arr[]) {
		count = 0;
		time = 0;
		previousMomentTime = System.nanoTime();
		sort(arr, 0, arr.length - 1);
	}
	void merge(int arr[], int l, int m, int r)
	{
		hakimOperation(1);
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;
		hakimOperation(5);

		/* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];
		hakimOperation(n1 + n2);

		/*Copy data to temp arrays*/
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		hakimOperation(1 + (n1 + 1) + (2 * n1) + (2 * n1));
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];
		hakimOperation(1 + (n2 + 1) + (3 * n2) + (2 * n2));

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;
		hakimOperation(2);

		// Initial index of merged subarray array
		int k = l;
		hakimOperation(1);
		while (i < n1 && j < n2) {
			hakimOperation(3);
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
				hakimOperation(5);
			}
			else {
				arr[k] = R[j];
				j++;
				hakimOperation(3);
			}
			k++;
			hakimOperation(2);
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
			hakimOperation(6);
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
			hakimOperation(6);
		}
	}

	// Main function that sorts arr[l..r] using
	// merge()
	void sort(int arr[], int l, int r)
	{
		hakimOperation(1);
		if (l < r) {
			// Find the middle point
			int m = l + (r - l) / 2;
			hakimOperation(5);

			// Sort first and second halves
			sort(arr, l, m);
			sort(arr, m + 1, r);

			// Merge the sorted halves
			merge(arr, l, m, r);
		}
	}

	public int getCount() {
		return count;
	}

	public long getTime() {
		return time;
	}

	public void hakimOperation(long additionalCount) {
		time += System.nanoTime() - previousMomentTime;
		count += additionalCount;
		previousMomentTime = System.nanoTime();
	}

}
