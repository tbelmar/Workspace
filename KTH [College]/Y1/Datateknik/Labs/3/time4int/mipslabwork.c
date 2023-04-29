/* mipslabwork.c

   This file written 2015 by F Lundevall
   Updated 2017-04-21 by F Lundevall

   This file should be changed by YOU! So you must
   add comment(s) here with your name(s) and date(s):

   This file modified 2017-04-31 by Ture Teknolog 

   For copyright and licensing, see file COPYING */

#include <stdint.h>   /* Declarations of uint_32 and the like */
#include <pic32mx.h>  /* Declarations of system-specific addresses etc */
#include "mipslab.h"  /* Declarations for these labs */

//#define PIC32_R(a) *(volatile unsigned*)(0xBF800000 + (a))
//#define TRISE PIC32_R(0x86100)
//#define PORTE PIC32_R(0x86110)

int prime = 1234567;
int ticks = 0x00;
int timeoutcount = 0;

int mytime = 0x5957;

char str[] = "hhhhh";
int othertime = 0xAAAA;

char textstring[] = "text, more text, and even more text!";

/* Interrupt Service Routine */
//void __attribute__((interrupt)) 
void user_isr( void )
{	
	if (timeoutcount++ % 10 == 0) {
		time2string(textstring, mytime);
		display_string(3, textstring);
		display_update();
		tick(&mytime);
	}

	if ((IFS(0) >> 8) & 0x1 == 0x1)
		IFS(0) &= ~0x100;

	if ((IFS(0) >> 19) & 0x1 == 0x1) {
		PORTE++;
		IFS(0) &= ~0x80000;
	}
	
	return;
}

/* Lab-specific initialization goes here */
void labinit( void )
{
	TRISE &= ~0xFF;
	TRISD |= 0xFE0;

	T2CON = 0x0070;
	PR2 = 0x7A12;
	TMR2 = 0;
	T2CON = 0x8070;

	IPC(2) = 0x7 << 2 | 0x3;
	IEC(0) |= 0x80100;

	IPC(4) |= 0x1C0000;

	IFS(0) = 0x0;

	PORTE = 0x0;

	enable_interrupt();
}

/* This function is called repetitively from the main program */
void labwork( void ) {
	prime = nextprime(prime);
	display_string(0, itoaconv(prime));
	display_update();
}