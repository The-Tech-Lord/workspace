#include <stdio.h>
#include <stdlib.h>

int k_linear_selection(int *array, int arr_size, int k) {
	if (arr_size == 1)
		return array[0];
	
	// Sort array from least to greatest
	for (int i = 0; i < arr_size - 1; i++) {
		int minj = i;
		for (int j = i + 1; j < arr_size; j++)
			if (array[j] < array[minj])
				minj = j;

		if (i != minj) {
			int temp = array[i];
			array[i] = array[minj];
			array[minj] = temp;
		}
	}

	// Find the kth largest integer
	int kth_largest = array[arr_size - 1];
	k--;
	for (int i = arr_size - 1; i >= 0; i--) {
		if (k == 0)
			break;

		// Simple way to deal with duplicate numbers
		if (array[i - 1] < kth_largest) {
			kth_largest = array[i - 1];
			k--;
		}
	}

	return kth_largest;
}

int main() {
	int array_size = 0;
	int k = 0;

	printf("Enter array size: ");
	scanf_s("%d", &array_size);
	printf("Enter k value: ");
	scanf_s("%d", &k);

	int *number_array = (int *)malloc(sizeof(int) * array_size);
	if (number_array == NULL) {
		printf("Memory for number_array did not allocate properly...exiting");
		return -1;
	}
	
	printf("Enter elements: ");
	for (int i = 0; i < array_size; i++)
		scanf_s("%d", &number_array[i]);

	int kth_number = k_linear_selection(number_array, array_size, k);
	for (int i = 0; i < array_size; i++)
		printf("%d ", number_array[i]);
	printf("\n");
	printf("%d\n", kth_number);
}
