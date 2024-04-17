/* Project: CircleRadius
 * Name   : MD Raiyan Hossain
 * Date   : 2/6/2024
 * File   : Q2.c
 * Notes  : Provides the area and perimeter of a circle given the radius
 */

#include <stdio.h>

int main() {
	double radius, area, perimeter;

	// Make PI variable constant
	const double PI = 3.14;

	// Prompt
	printf("Please enter the radius to calculate the perimeter and area of a circle: ");
	scanf("%lf", &radius);

	// Logic
	perimeter = 2 * PI * radius;
	area = PI * radius * radius;

	// Output
	printf("Perimeter: %f inches\n", perimeter);
	printf("Area: %f square inches\n", area);
	return 0;
}
