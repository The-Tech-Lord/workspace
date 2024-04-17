/* Project: Q1
 * Name   : MD Raiyan Hossain
 * Date   : 2/20/2024
 * File   : Q1.c
 * Notes  : Prints stars in a backwards triangle pattern
 */

#include <stdio.h>

int main() {
	int rows = 0;
	
	printf("Enter the number of rows: ");
	scanf("%d", &rows);

	for (int i = 0; i <= rows; i++) {
		// Reduce the number held within the spaces variable while
		// printing the appropriate amount of space
		for (int spaces = rows - 1 - i; spaces >= 0; spaces--) {
			printf(" ");
		}
		for (int j = 0; j < i; j++) {
			printf("*");
		}
		printf("\n");
	}
	
	return 0;
}
