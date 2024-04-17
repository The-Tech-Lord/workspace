#include <stdio.h>

void swapVarNum(int *num1, int *num2) {
	int temp = *num2;
	*num2 = *num1;
	*num1 = temp;
}

int main() {
	int a = 5, b = 6;
	swapVarNum(&a, &b);
	printf("a = %d\nb = %d\n", a, b);
}
