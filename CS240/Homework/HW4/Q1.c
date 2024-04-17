/* Project: VowelConsonantCounter
 * Name   : MD Raiyan Hossain
 * Date   : 4/11/2024
 * File   : Q1.c
 * Notes  : Takes a string input and counts the number of vowels and consonants in the string
 */

#include <stdio.h>
#include <stdlib.h>

void vowel_consonant_counter(const char *input, const int *length, int *vowels, int *consonants) {
	char vowels_list[] = {
		'a', 'e', 'i', 'o', 'u',
		'A', 'E', 'I', 'O', 'U'
	};
	char consonants_list[] = {
		'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
	};

	for (int i = 0; i < *length; i++) {
		// Continue if character is a number
		if (input[i] >= '0' && input[i] <= '9') {
			continue;
		}
		// Continue if character is a space
		if (input[i] == ' ') {
			continue;
		}
		// Continue if character is a explanation mark
		if (input[i] == '!') {
			continue;
		}

		// Check for vowels
		for (int j = 0; j < (int)(sizeof(vowels_list) / sizeof(vowels_list[0])); j++) {
			if (input[i] == vowels_list[j] || input[i] == vowels_list[j]) {
				*vowels = *vowels + 1;
				break;
			}
		}

		// Check for consonants
		for (int j = 0; j < (int)(sizeof(consonants_list) / sizeof(consonants_list[0])); j++) {
			if (input[i] == consonants_list[j] || input[i] == consonants_list[j]) {
				*consonants = *consonants + 1;
				break;
			}
		}
	}
}

int main() {
	char string_length_char[10];
	int string_length = 0, vowels = 0, consonants = 0;
	
	printf("Pointer : count the number of vowels and consonants :\n");
	printf("---------------------------------------------------------\n");
	printf("Input the length of your string: ");

	// This is a bit of a cursed way to get the integer through `fgets`
	// I was originally wanting to do all the inputs through `scanf`, but couldn't get it to "ignore" whitespace
	// so I ended up with just the "Hello" part of the inputted example string.
	// Help Source: https://stackoverflow.com/a/40957702/11615909
	fgets(string_length_char, (int)(sizeof(string_length_char) / sizeof(string_length_char[0])), stdin);
	string_length = atoi(string_length_char);
	
	printf("Input a string: ");

	char *string_input = (char *)malloc(string_length * sizeof(char) + 1);
	fgets(string_input, string_length * sizeof(char) + 1, stdin);
	
	vowel_consonant_counter(string_input, &string_length, &vowels, &consonants);

	printf("Number of vowels: %d\n", vowels);
	printf("Number of consonants: %d\n", consonants);

	free(string_input);
	
	return 0;
}
