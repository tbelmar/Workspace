// Written by A Hammarstrand

#ifndef BUTTONS_h
#define BUTTONS_h
#include <pic32mx.h>

#define BTN1 ((PORTF >> 1) & 1)
#define BTN2 (( PORTD >> 5) & 1 )
#define BTN3 (( PORTD >> 6) & 1 )
#define BTN4 (( PORTD >> 7) & 1 )
#define BTNA ((( PORTD >> 4) & 0xe) | BTN1 )

#define BTN1_VAL BTN1
#define BTN2_VAL (PORTD & (1 << 5))
#define BTN3_VAL (PORTD & (1 << 6))
#define BTN4_VAL (PORTD & (1 << 7))
#define BTNA_VAL ((PORTD & (7 << 5)) | BTN1)

#define SWT1 ((PORTD >> 8) & 1)
#define SWT2 ((PORTD >> 9) & 1)
#define SWT3 ((PORTD >> 10) & 1)
#define SWT4 ((PORTD >> 11) & 1)
#define SWTA ((PORTD >> 8) & 0xf)

#define SWT1_VAL (PORTD & (1 << 8))
#define SWT2_VAL (PORTD & (1 << 9))
#define SWT3_VAL (PORTD & (1 << 10))
#define SWT4_VAL (PORTD & (1 << 11))
#define SWTA_VAL (PORTD & (0xf << 8))

#endif
