#include <stdio.h>
#include <stdlib.h>

char* getInput(int *count) {
	// Apparently I was getting errors from `realloc` that the
	// "next size" was invalid. Realized that I was still using the
	// size of the pointer itself.
	int input_length_count = 2;
	
	char *input = (char *)malloc(sizeof(char) * input_length_count);

	// Check if the memory allocated correctly
	// If not, exit the program
	if (input == NULL) {
		printf("getInput function failed to allocate memory");
		exit(1);
	}

	for (int i = 0;; i++) {
		// Source for `fgetc` usage with `stdin`: https://stackoverflow.com/a/5789627/11615909
		char c = fgetc(stdin);

		if (feof(stdin)) {
			break;
		}

		if (i == input_length_count) {
			input = (char *)realloc(input, input_length_count * 2);
			input_length_count *= 2;
		}

		input[i] = c;

		// Increment the value passed in as `count`
		++(*count);
	}

	return input;
}

char* reverseString(const char *str, const int *count) {
	// Allocate memory, amounted to that of the size of `str`.
	// Because it's not possible to get the length of allocated memory in C
	// we need to pass in another parameter that has the `length` of the
	// inputted string from standard input, using `count`.
	char *reversed_string = (char *)malloc(sizeof(char) * (*count));

	// Check if the memory allocated correctly
	// If not, exit the program
	if (reversed_string == NULL) {
		printf("reverseString function failed to allocate memory");
		exit(1);
	}

	for (int i = 0; i < *count; i++) {
		reversed_string[i] = str[i];
	}

	// Traverse `reversed_string` and swap values
	for (int i = 0, j = (*count) - 2;  // Have `j` set to `(*count) - 2` so that we skip the `\n` character
		 i < (int)((*count) / 2) && j > i + 1;
		 i++, j--) {
		
		char temp = reversed_string[i];
		reversed_string[i] = reversed_string[j];
		reversed_string[j] = temp;
	}

	return reversed_string;
}

int main() {
	printf("Enter a string: ");

	// Didn't want to have a global scope variable
	// so I just put it inside the `main` function
	// and had it get passed in as an argument
	int string_length = 0;
	
    char *str = getInput(&string_length);
    char *reversed = reverseString(str, &string_length);
	
    printf("Reversed string: %s\n", reversed);

	free(str);
	free(reversed);
	
    return 0;
}
