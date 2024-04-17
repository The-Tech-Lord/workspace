#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* dyn_strcpy(const char* source) {
    // Check if source is `NULL` first, then dynamically allocate memory and lastly copy string
	if (source == NULL) {
		printf("source is NULL");
		exit(1);
	}

	int length = strlen(source);
	char *message = (char *)malloc(length * sizeof(char));

	// Check if memory was properly allocated to `message`
	if (message == NULL) {
		printf("dyn_strcpy function failed to allocate memory");
		exit(1);
	}

	strcpy(message, source);

	return message;
}

// Dynamic string concatenation function
char* dyn_strcat(char* destination, const char* source) {
    // If source is null, then return destination
    // If destination is NULL, copy source to destination
    if (source == NULL) {
		return destination;
	}
	if (destination == NULL) {
		return NULL;
	}
	
	int source_length = strlen(source);	
	int destination_length = strlen(destination);

	// Reallocate memory for the new string
    // Concatenate the source string to the destination string, use realloc to allocate memory for the new string
	destination = (char *)realloc(destination, (destination_length + source_length) * sizeof(char));

	// If the destination is NULL, then return NULL
    // Concatenate the source string to the destination string
	if (destination == NULL) {
		printf("(dyn_strcat) memory failed to allocated for: `destination`");
		return NULL;
	}

	strcat(destination, source);

    return destination;
}

// Dynamic string comparison function
int dyn_strcmp(const char* str1, const char* str2) {	
    //while both strings are not null and the characters are the same, increment the pointers
	while ((str1 != NULL && str2 != NULL) && (*str1 == *str2)) {
		str1++;
		str2++;
	}

	int lex_diff = *str1 - *str2;

    //return the difference between the characters
    return lex_diff;
}

int main() {
    // Driver code
    // String copy
    const char* hello = "Hello, ";
    const char* world = "world!";
    char* message = dyn_strcpy(hello);
	message = dyn_strcat(message, world);
    printf("Concatenated string: %s\n", message);

    // String comparison
    const char* s1 = "Hello";
    const char* s2 = "World";
    printf("Comparing '%s' and '%s': %d\n", s1, s2, dyn_strcmp(s1, s2));


    free(message);

    return 0;
}
