/* Project: Arrays
 * Name   : MD Raiyan Hossain
 * Date   : 3/29/2024
 * File   : Q1.c
 * Notes  : Returns the number of duplicates found from user input
 */

#include <stdio.h>
#include <stdbool.h>

int main() {
	int inputs[100];
	int input_num;

	// Used for checking duplicates
	int duplicates[100];
	int duplicate_num = 0;

	// Input Prompts
	printf("Input the number of elements to be stored in the array : ");
	scanf("%d", &input_num);
	printf("Input 5 elements in the array :\n");

	for (int i = 0; i < input_num; i++) {
		printf("element - %d : ", i);
		scanf("%d", &inputs[i]);
	}

	// I personally think this is cursed, but if it works, it works
	for (int i = 0; i < input_num; i++) {
		for (int j = i + 1; j < input_num; j++) {
			// Since I doubt we can use `goto` statements right now, this is the
			// best alternative I can come up with. This is used in a duplicate checking
			// loop found further below that will mark if the value at the current index
			// in `inputs` is already considered a duplicate (found in `duplicates[]`).
			bool isAlreadyConsideredDuplicate = false;
			
			if (inputs[i] == inputs[j]) {
				// Check if there is already a number considered as a duplicate
				// If no duplicates exist, then we break out of this loop
				for (int k = 0; k < duplicate_num && duplicate_num > 0; k++) {
					if (inputs[j] == duplicates[k])	{
						isAlreadyConsideredDuplicate = true;
						break;
					}
				}
				
				if (isAlreadyConsideredDuplicate)
					break;

				// Add the duplicate number to another array for future checking
				// Then increment the `duplicate_num` variable
				duplicates[duplicate_num] = inputs[i];
				duplicate_num++;
			}
		}
	}

	if (duplicate_num <= 0) {
		printf("\nThere are no duplicate elements in the array\n");
	} else {
		printf("\nTotal number of duplicate elements found in the array: %d\n", duplicate_num);
	}
	
	return 0;
}
