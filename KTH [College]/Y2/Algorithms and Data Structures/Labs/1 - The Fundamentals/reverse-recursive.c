#include <stdio.h>

void reverse() {
	char c = getchar();
	if (c == '\n')
		return;

	reverse();
	putchar(c);
}

int main() {
	printf("Please input your string.\n")
	reverse();
}