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

volatile unsigned* trisE = (unsigned*)0xBF886100;
volatile unsigned* portE = (unsigned*)0xBF886110;

int ticks = 0x00;

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
	*trisE &= ~0xFF;
	TRISD |= 0xFE0;
	return;
}

/* This function is called repetitively from the main program */
void labwork( void )
{
	*portE &= ~0xFF;
	*portE |= ticks;
	delay( 1000 );

	// gets info from buttons and switches
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

	time2string(textstring, mytime);

	display_string( 3, textstring );
	display_update();
	tick( &mytime );
	ticks++;
	display_image(96, icon);
}