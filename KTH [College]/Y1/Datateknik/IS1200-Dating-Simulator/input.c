/*
    Written by: H Hagberg
    Assistence: A Hammarstrand
*/

#include <stdint.h>
#include <pic32mx.h>
#include "input.h"

int getbtns (void) {
    return (((PORTD >> 4) & 0xe) | ((PORTF >> 1) & 0x1));
}
