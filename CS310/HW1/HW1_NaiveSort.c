/* Name: MD Raiyan Hossain
 * Course: CS310
 */

#include <stdio.h>
#include <stdlib.h>

void insertion_sort(int *arr, int size) {
	for (int i = 1; i < size; i++) {
		for (int j = i; j > 0 && (arr[j - 1] > arr[j]); j--) {
			int temp = arr[j];
			arr[j] = arr[j - 1];
			arr[j - 1] = temp;
		}
	}
}

// Merge two arrays into one array that contains both array's elements
int* merge(int* arr1, int size1, int* arr2, int size2) {
	int *merged_arr = (int*)malloc((size1 + size2) * sizeof(int));
	int index = 0;

	// Merge arrays
	for (int i = 0; i < size1; i++, index++)
		merged_arr[i] = arr1[i];
	for (int i = 0; i < size2; i++, index++)
		merged_arr[index] = arr2[i];
	
	return merged_arr;
}

int main(int argc, char *argv[]) {
	if (argc < 3) {
		printf("Please input 2 array sizes");
		return -1;
	}

	// Setup
	int num1_size = atoi(argv[1]), num2_size = atoi(argv[2]);
	int* num1 = (int*)malloc(m * sizeof(int));
	if (num1 == NULL) {
		printf("Memory for num1 didn't allocate properly... exiting\n");
		return -1;
	}
	int* num2 = (int*)malloc(n * sizeof(int));
	if (num2 == NULL) {
		printf("Memory for num2 didn't allocate properly... exiting\n");
		return -1;
	}

	// Input
	printf("Enter elements of first array:\n");
	for (int i = 0; i < num1_size; i++)
		scanf("%d", &num1[i]);
	printf("Enter elements of second array:\n");
	for (int i = 0; i < num2_size; i++)
		scanf("%d", &num2[i]);

	// Merge arrays and sort
	num1 = merge(num1, num1_size, num2, num2_size);
	insertion_sort(num1, num1_size + num2_size);

	for (int i = 0; i < m + n; i++)
		printf("%d ", num1[i]);
	printf("\n");

	free(num1);
	free(num2);

	return 0;
}
