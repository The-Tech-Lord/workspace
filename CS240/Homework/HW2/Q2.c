/* Project: Q2
 * Name   : MD Raiyan Hossain
 * Date   : 2/20/2024
 * File   : Q2.c
 * Notes  : Prints the LCM of two integers
 */

#include <stdio.h>

int main() {
	int num1, num2, max, lcm;
	
	printf("Input 1st number for LCM: ");
	scanf("%d", &num1);
	printf("Input 2nd number for LCM: ");
	scanf("%d", &num2);

	// Find max num
	max = (num1 > num2) ? num1 : num2;

	// We can use max as a starting point since we're finding a common multiple
	lcm = max;

	// Calculate the LCM
	// Since we're strictly using for-loops, decided to make it "basically" infinite as this was the best
	// solution I could come up with
	for (int i = 0; i < i + 1; i++) {
		// Both num1 and num2 will need to have no remainder to show that they both evenly divide lcm
		// and since the first ever match will be the lowest, it is therefore the lcm
		if (lcm % num1 == 0 && lcm % num2 == 0) {
			break;
		}

		// Go through every possible number
		lcm++;
	}

	printf("LCM of 30 and 22 = %d\n", lcm);
	
	return 0;
}
