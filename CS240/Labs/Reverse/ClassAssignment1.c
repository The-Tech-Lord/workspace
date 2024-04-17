/* Project: ClassAssignment1
 * Name   : MD Raiyan Hossain
 * Date   : 2/15/2024
 * File   : ClassAssignment1.c
 * Notes  : Reverses inputted string
 */

#include <stdio.h>
#include <string.h>
#include <math.h>

int main() {
	int inputStrSize = 100;
	char inputStr[inputStrSize + 1];
	
	printf("Enter a sring: ");
	fgets(inputStr, sizeof(inputStr), stdin);

	// Take into account the newline charater at the end of the array with the j variable
	for (int i = 0, j = strlen(inputStr) - 2; i < (int)floor((int)strlen(inputStr) / 2); i++, j--) {
		int temp = inputStr[i];
		
		inputStr[i] = inputStr[j];		
		inputStr[j] = temp;

		//printf("%s %d %d %c %c\n", inputStr, i, j, inputStr[i], inputStr[j]);
	}
	
	printf("%s\n", inputStr);
	
	return 0;
}
