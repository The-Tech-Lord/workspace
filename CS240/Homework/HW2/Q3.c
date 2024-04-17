/* Project: Q3
 * Name   : MD Raiyan Hossain
 * Date   : 2/20/2024
 * File   : Q3.c
 * Notes  : Implements series to produce an output
 */

#include <stdio.h>
#include <math.h>

int main() {
	int x_val;
	int terms;
	int result;

	printf("Input the value of x: ");
	scanf("%d", &x_val);
	printf("Input number of terms: ");
	scanf("%d", &terms);
	printf("The values of the series:\n");
	
	for (int i = 0, exp = 1; i < terms; i++, exp += 2) {
		// Wasn't able to get a proper sign flipping so I decided to use a ternary operator for a one-liner
		result = (result > 0) ? result - pow(x_val, exp) : result + pow(x_val, exp);

		printf("%d\n", result);
	}

	printf("The sum: %d\n", result);
	
	return 0;
}
