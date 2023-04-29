#include <stdint.h>
#include <pic32mx.h>
#include "mipslab.h" 

int getsw(void) {
	int switchInformation = PORTD >> 8;
	switchInformation &= 0xF;
	return switchInformation;
}

int getbtns(void) {
	int buttonInformation = PORTD >> 5;
	buttonInformation &= 0x7;
	return buttonInformation;
}