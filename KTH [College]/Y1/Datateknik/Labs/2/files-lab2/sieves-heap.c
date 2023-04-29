/*
 sieves-heap.c
 By Tomás Belmar.
 Last modified: 2021-02-10
 This file is in the public domain.
*/

#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <stdbool.h>
#include <time.h>

#define COLUMNS 6
int numsInRow = 0;

void print_number(int n) {
    printf("%10d ", n);
    if (++numsInRow % COLUMNS == 0)
        printf("\n");
}

void print_sieves(int n) {
    if (n < 2)
        return;
    n++;

    bool* sieve = malloc(n-2);

    int i;
    for (i = 0; i < n-2; i++)
        *(sieve+i) = true;

    for (i = 0; i < n - 2; i++) {
        if (*(sieve+i)) {
            int j;
            for (j = 2 * (i + 2); j <= n + 2; j += (i + 2))
                *(sieve + j - 2) = false;
        }
    }

    for (i = 0; i <= n - 2; i++)
        if (*(sieve+i))
            print_number(i + 2);

    free(sieve);

    printf("\n");
}

// 'argc' contains the number of program arguments, and
// 'argv' is an array of char pointers, where each
// char pointer points to a null-terminated string.
int main(int argc, char* argv[]) {
    clock_t start, end;
    start = clock();
    if (argc == 2)
        print_sieves(atoi(argv[1]));
    else
        printf("Please state an integer number.\n");
    end = clock();

    printf("Time taken: %f", ((double)(end - start)));
    return 0;
}