/* Project: Arrays
 * Name   : MD Raiyan Hossain
 * Date   : 3/29/2024
 * File   : Q1.c
 * Notes  : Enter, sort, and print the even and odd numbers
 */

#include <stdio.h>

int main() {
	int inputs[100];
	int even_inputs[100], odd_inputs[100];

	// Since we are not allowed to use dynamic memory allocation, a solution I came up with was to
	// create variables that would hold the number of even and odd values and then can use those to
	// traverse through the array later. This also solves the problem of printing the value 0 that's
	// stored in the array by default.
	int even_num = 0, odd_num = 0;

	// Holds number of inputs
	int input_num;
	
	// Input Prompts
	printf("Separate odd and even integers in separate arrays:\n");
	printf("------------------------------------------------------\n");
	printf("Input the number of elements to be stored in the array : ");
	scanf("%d", &input_num);
	printf("Input %d elements in the array :\n", input_num);

	// Input prompt loop
	for (int i = 0; i < input_num; i++) {
		printf("element - %d : ", i);
		scanf("%d", &inputs[i]);
	}

	// Check for even and odd values
	for (int i = 0; i < input_num; i++) {
		if (inputs[i] % 2 == 0) {
			even_inputs[even_num] = inputs[i];
			even_num++;
		} else {
			odd_inputs[odd_num] = inputs[i];
			odd_num++;
		}
	}

	// Print even and odd values in arrays
	if (even_num <= 0) {
		printf("There are no even elements in the given inputs\n");
	} else {
		printf("The even elements are :\n");
		for (int i = 0; i < even_num; i++) {
			printf("%d ", even_inputs[i]);
		}
	}

	if (odd_num <= 0) {
		printf("There are no odd elements in the given inputs\n");
	} else {
		printf("\nThe odd elements are :\n");
		for (int i = 0; i < odd_num; i++) {
			printf("%d ", odd_inputs[i]);
		}
		printf("\n");
	}

	return 0;
}
