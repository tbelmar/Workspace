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

int ticks = 0x00;
int timeoutcount = 0;

int mytime = 0x5957;

char textstring[] = "text, more text, and even more text!";

/* Interrupt Service Routine */
void user_isr( void )
{
  return;
}

/* Lab-specific initialization goes here */
void labinit( void )
{
	TRISE &= ~0xFF;
	TRISD |= 0xFF;

	T2CON = 0x0070;
	PR2 = 0x7A12;
	TMR2 = 0;
	T2CON = 0x8070;

	return;
}

/* This function is called repetitively from the main program */
void labwork(void)
{
	PORTE &= ~0xFF;
	PORTE |= ticks;

	int btnInfo = getbtns();
	int switchInfo = getsw();

	if ((btnInfo & 1) == 1) {
		mytime &= 0xFF0F;
		mytime |= switchInfo << 4;
	}
	if (((btnInfo >> 1) & 1) == 1) {
		mytime &= 0xF0FF;
		mytime |= switchInfo << 8;
	}
	if (((btnInfo >> 2) & 1) == 1) {
		mytime &= 0x0FFF;
		mytime |= switchInfo << 12;
	}

	while (((IFS(0) >> 8) & 0x1) != 1);

	IFS(0) = 0;

	if (timeoutcount++ % 10 == 0) {
		time2string(textstring, mytime);

		display_string(3, textstring);
		display_update();
		tick(&mytime);

		ticks++;
		display_image(96, icon);
	}
}