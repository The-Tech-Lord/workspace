#include <stdio.h>
#include <stdlib.h>

void swap(int* array, int index1, int index2) {
	int temp = array[index1];
	array[index1] = array[index2];
	array[index2] = temp;
}

int partition(int* array, int start, int end) {
	int pivot = array[end];
	int index = start - 1;

	for (int i = start; i <= end; i++)
		printf("%d ", array[i]);
	printf("\n%d\n", array[end]);

	for (int i = start; i < end; i++) {
		if (array[i] <= pivot) {
			index++;
			printf("Swapping (%d & %d): %d and %d\n", index, i, array[index],
				   array[i]);
			for (int j = start; j < end; j++)
				printf("%d ", array[j]);
			printf("\n");

			swap(array, index, i);
			
			for (int j = start; j < end; j++)
				printf("%d ", array[j]);
			printf("\n");
			printf("\n");
		}
	}
	swap(array, index + 1, end);
	for (int i = start; i < end; i++)
		printf("%d ", array[i]);
	printf("%d\n", array[end]);
	return index + 1;
}

void quicksort(int* array, int start, int end) {
	if (start < end) {
		int split = partition(array, start, end);
		quicksort(array, start, split - 1);
		quicksort(array, split + 1, end);
	}
}

int main() {
	int array_size = 0;

	printf("Enter array size: ");
	scanf_s("%d", &array_size);

	int* number_array = (int *)malloc(sizeof(int) * array_size);
	if (number_array == NULL) {
		printf("Memory for number_array did not allocate properly...exiting");
		return -1;
	}
	
	printf("Enter elements: ");
	for (int i = 0; i < array_size; i++)
		scanf_s("%d", &number_array[i]);

	quicksort(number_array, 0, array_size - 1);
	for (int i = 0; i < array_size; i++)
		printf("%d ", number_array[i]);
	printf("\n");
}
