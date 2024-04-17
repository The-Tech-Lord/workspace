/* Project: LargestInt
 * Name   : MD Raiyan Hossain
 * Date   : 2/6/2024
 * File   : Q3.c
 * Notes  : Prints the largest integer out of 3 inputted integers
 */

#include <stdio.h>
#include <stdlib.h>

int main() {
	int num1, num2, num3;
	float start_int;
	float max_int;

	// Prompts
	printf("Input first integer: ");
	scanf("%d", &num1);
	printf("Input second integer: ");
	scanf("%d", &num2);
	printf("Input third integer: ");
	scanf("%d", &num3);

	// Arithmetic Logic
	// https://math.stackexchange.com/a/617089/699569
	start_int = (0.5 * num1) + (0.5 * num2) + (0.5 * abs(num1 - num2));
	start_int = (0.5 * start_int) + (0.5 * num3) + (0.5 * abs((int)start_int - num3));

	max_int = start_int;

	// Output
	printf("Max value: %f\n", max_int);
	return 0;
}
