/*
 sieves.c
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
    double sum = 0;
    double numOfElements = 0;

    if (n < 2)
        return;
    n++;

    bool sieve[n-2];
    int i;

    for (i = 0; i < n-2; i++)
        sieve[i] = true;

    for (i = 0; i < n-2; i++) {
        if (sieve[i]) {
            int j;
            for (j = 2*(i+2); j <= n+2; j += (i+2))
                sieve[j-2] = false;
        }
    }

    int currentPrime = -1;

    for (i = 0; i <= n; i++) {
        if (sieve[i]) {
            if (currentPrime == -1)
                currentPrime = i + 2;
            else {
                sum += (i + 2) - currentPrime;
                numOfElements++;
            }

            print_number(i + 2);
            currentPrime = i + 2;
        }
    }

    printf("Average distance between primes: %d/%d\n", sum, numOfElements);

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
    printf("Time taken: %f\n", (double)(end - start));

    /*
    if (numOfElements != 0) {
        //printf("Average distance between primes: %d/%d\n", sum, numOfElements);
        //printf("Average distance between primes: %d\n", dSum);
    }
    */
    return 0;
}