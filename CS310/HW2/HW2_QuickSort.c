#include <stdio.h>
#include <stdlib.h>

void partition(int* array, int arr_size, int begin, int end) {

}

void quicksort(int* array, int arr_size) {
	
}

int main() {
	int array_size = 0;

	printf("Enter array size: ");
	scanf_s("%d", &array_size);

	int *number_array = (int *)malloc(sizeof(int) * array_size);
	if (number_array == NULL) {
		printf("Memory for number_array did not allocate properly...exiting");
		return -1;
	}
	
	printf("Enter elements: ");
	for (int i = 0; i < array_size; i++)
		scanf_s("%d", &number_array[i]);
}
