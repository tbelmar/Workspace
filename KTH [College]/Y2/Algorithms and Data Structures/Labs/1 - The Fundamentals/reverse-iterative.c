#include <stdio.h>

void reverse(int len) {
	char reverseStr[len];
	
	int i;
	for (i = len; i >= 0; i--)
		reverseStr[i] = getchar();

	for (i = 0; i < len; i++)
		putchar(reverseStr[i]);
}

int main() {
	printf("What is the length of your string?\n");

	int n;
	scanf("%d", &n);

	printf("Please input your string.\n");
	reverse(n);
}