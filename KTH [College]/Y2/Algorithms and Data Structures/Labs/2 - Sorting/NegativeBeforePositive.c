#include <stdio.h>

void swap(int* arr, int i, int j) {
	int temp = *(arr + i);
	*(arr + i) = *(arr + j);
	*(arr + j) = temp;
}

void negBeforePos(int* arr, int size) {
	int i;
	for (i = 0; i < size; i++)
		if (*(arr+i) >= 0) {
			int j;
			for (j = i+1; j < size; j++)
				if (*(arr + j) < 0)
					swap(arr, i, j);
		}
}

void printArray(int* arr, int size) {
	for (; size > 0; size--)
		if (size != 1)
			printf("[%d], ", *(arr++));
		else
			printf("[%d]\n", *(arr++));
}

int main() {
	int size = 10;
	int arr[size];

	arr[0] = 23;
	arr[1] = 19;
	arr[2] = -3;
	arr[3] = 5;
	arr[4] = 0;
	arr[5] = -27;
	arr[6] = 16;
	arr[7] = -10;
	arr[8] = -1;
	arr[9] = 22;

	printArray(arr, size);

	negBeforePos(arr, size);
	printArray(arr, size);
}