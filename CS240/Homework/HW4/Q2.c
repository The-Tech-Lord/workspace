/* Project: BubbleSort
 * Name   : MD Raiyan Hossain
 * Date   : 4/11/2024
 * File   : Q2.c
 * Notes  : Sorts an array of given inputs using Bubble Sort
 */

#include <stdio.h>
#include <stdlib.h>

// This took WAY too long to figure out because I realized that I was using the wrong
// for-loop variable to do the index comparisons. You do not want to know what cursed
// code I had come up with before this final result.
void bubblesort_ascending(int *values_array, const int length) {
	for (int i = 0; i < length - 1; i++) {
		for (int j = 0; j < length - i - 1; j++) {
			if (values_array[j] > values_array[j + 1]) {
				int temp = values_array[j];
				values_array[j] = values_array[j + 1];
				values_array[j + 1] = temp;
			}
		}
	}
}

void bubblesort_descending(int *values_array, const int length) {
	for (int i = 0; i < length - 1; i++) {
		for (int j = 0; j < length - i - 1; j++) {
			if (values_array[j] < values_array[j + 1]) {
				int temp = values_array[j];
				values_array[j] = values_array[j + 1];
				values_array[j + 1] = temp;
			}
		}
	}
}

int main() {
	int number_of_values;
	int *values;
	int sorting_order;

	printf("Please input the number of values in your array : ");
	scanf("%d", &number_of_values);
	printf("Input your array values, you should input 5 values :\n");

	// Input elements
	values = (int *)malloc(sizeof(int) * number_of_values);
	for (int i = 0; i < number_of_values; i++) {
		printf("element - %d : ", i + 1);
		scanf("%d", &values[i]);
	}

	printf("Choose sorting order:\n");
	printf("1. Ascending\n");
	printf("2. Descending\n");

	// To account for incorrect inputs, we put the statements in a `while` loop
	while (1) {
		printf("Order: ");
		scanf("%d", &sorting_order);

		if (sorting_order == 1) {
			bubblesort_ascending(values, number_of_values);
			break;
		} else if (sorting_order == 2) {
			bubblesort_descending(values, number_of_values);
			break;
		} else {
			printf("Input a valid option\n");
		}
	}

	for (int i = 0; i < number_of_values; i++) {
		printf("element - %d : %d\n", i + 1, values[i]);
	}

	return 0;
}
