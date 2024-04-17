/* Project: Arrays
 * Name   : MD Raiyan Hossain
 * Date   : 3/29/2024
 * File   : Q1.c
 * Notes  : Returns the unique numbers in an array of inputs
 */

#include <stdio.h>
#include <stdbool.h>

int main() {
	int inputs[100];
	int input_num;

	int unique[100];
	int unique_num = 0;

	// Input Prompts
	printf("Print all unique elements of an array:\n");
	printf("------------------------------------------\n");
	printf("Input the number of elements to be stored in the array: ");
	scanf("%d", &input_num);
	printf("Input 5 elements in an array :\n");

	for (int i = 0; i < input_num; i++) {
		printf("element - %d : ", i);
		scanf("%d", &inputs[i]);
	}

	for (int i = 0; i < input_num; i++) {
		bool isUnique = true;

		// Check if the current index value is a duplicate
		// starting from `i + 1` to the end
		for (int j = i + 1; j < input_num; j++) {
			if (inputs[i] == inputs[j]) {
				isUnique = false;
				break;
			}
		}

		// Check if the current index value is a duplicate
		// starting from the beginning of he array to the current index
		for (int j = 0; j < i; j++) {
			if (inputs[i] == inputs[j]) {
				isUnique = false;
				break;
			}
		}
		
		if (isUnique) {
			unique[unique_num] = inputs[i];
			unique_num++;
		}
	}

	// Exception if there are no unique values at all
	if (unique_num <= 0) {
		printf("\nThere are no unique elements\n");
	} else {
		printf("\nThe unique elements found in the array are:\n");
		for (int i = 0; i < unique_num; i++) {
			printf("%d ", unique[i]);
		}
		printf("\n");
	}
	
	return 0;
}
