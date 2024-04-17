/* Project: NumberOfDays
 * Name   : MD Raiyan Hossain
 * Date   : 2/6/2024
 * File   : Q1.c
 * Notes  : Prints the amount of years, weeks, and days there are based on the inputted amount of days
 */

#include <stdio.h>

// Import booleans as they're not implemented by default
#include <stdbool.h>

int main() {
	int input_days = 0;
	int years = 0, weeks = 0, days = 0;

	printf("Please enter a number of days: ");
	scanf("%d", &input_days);

	// Calculate Years
	int temp_years = input_days % 365;
	years = (input_days - temp_years) / 365;
	input_days = temp_years;

	// Calculate Weeks
	int temp_weeks = input_days % 7;
	weeks = (input_days - temp_weeks) / 7;
	input_days = temp_weeks;

	// Calculate Days
	days = input_days;

	/* Beefed Up Answer
	// Assuming 365 days in a year
	while (true) {
		if (input_days > 365) {
			input_days -= 365;
			years++;
		} else {
			// As I'm utilizing infinite loops, break statements
			// become more important to get right
			break;
		}
	}

	while (true) {
		if (input_days > 7) {
			input_days -= 7;
			weeks++;
		} else {
			break;
		}
	}

	days = input_days;
	*/

	// Output
	printf("Number of years: %d\n", years);
	printf("Number of weeks: %d\n", weeks);
	printf("Number of days: %d\n", days);
	return 0;
}
