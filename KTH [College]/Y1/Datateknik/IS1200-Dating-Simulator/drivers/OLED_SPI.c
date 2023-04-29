/* 
    Written by: A Hammarstrand
    Based on files from: Axel Isaksson
*/


#include <stdint.h>   /* Declarations of uint_32 and the like */
#include <pic32mx.h>  /* Declarations of system-specific addresses etc */
#include "OLED_SPI.h" /* Declatations for these labs */
#include "font.h"

#define DISPLAY_CHANGE_TO_COMMAND_MODE (PORTFCLR = 0x10)
#define DISPLAY_CHANGE_TO_DATA_MODE (PORTFSET = 0x10)

#define DISPLAY_ACTIVATE_RESET (PORTGCLR = 0x200)
#define DISPLAY_DO_NOT_RESET (PORTGSET = 0x200)

#define DISPLAY_ACTIVATE_VDD (PORTFCLR = 0x40)
#define DISPLAY_ACTIVATE_VBAT (PORTFCLR = 0x20)

#define DISPLAY_TURN_OFF_VDD (PORTFSET = 0x40)
#define DISPLAY_TURN_OFF_VBAT (PORTFSET = 0x20)

static void quicksleep(int cyc) {
	for (; cyc > 0; cyc--);
}

uint8_t spi_send_recv(uint8_t data) {
	while (!(SPI2STAT & 0x08));
	SPI2BUF = data;
	while (!(SPI2STAT & 1));
	return SPI2BUF;
}

void display_init(void)
{
	DISPLAY_CHANGE_TO_COMMAND_MODE;
	quicksleep(10);
	DISPLAY_ACTIVATE_VDD;
	quicksleep(1000000);

	spi_send_recv(0xAE);
	DISPLAY_ACTIVATE_RESET;
	quicksleep(10);
	DISPLAY_DO_NOT_RESET;
	quicksleep(10);

	spi_send_recv(0x8D);
	spi_send_recv(0x14);

	spi_send_recv(0xD9);
	spi_send_recv(0xF1);

	DISPLAY_ACTIVATE_VBAT;
	quicksleep(10000000);

	spi_send_recv(0xA1);
	spi_send_recv(0xC8);

	spi_send_recv(0xDA);
	spi_send_recv(0x20);

	spi_send_recv(0xAF);
}

void display_write(void)
{
	int x, y, pixelColumn;

	for (y = pixelColumn = 0; y < 4; y++) {
		DISPLAY_CHANGE_TO_COMMAND_MODE;

		spi_send_recv(0x22);
		spi_send_recv(y);

		spi_send_recv(0x00);
		spi_send_recv(0x10);

		DISPLAY_CHANGE_TO_DATA_MODE;

		for (x = 0; x < 128; x++)
			spi_send_recv(screen_buffer[pixelColumn++]);
	}
}

void display_clear(void) {
	buffer_clear();
	display_write();
}

void buffer_clear(void) {
	int column;
	for (column = 0; column < 128 * 4; column++)
		screen_buffer[column] = 0;
}

void load_image(const uint8_t* data) {
	int column;
	for (column = 0; column < 128 * 4; column++)
		screen_buffer[column] |= ~data[column];
}

void load_string(int line, char* s){
	if (line < 0 || line >= 4 || !s)
		return;

	int character, charPixelColumn, charValue, cad;

	// lines are 128 bits wide (128 elements), in memory rows follow each other
	int row = line * 128;

	for (character = 0; character < 16; character++, s++){
		charValue = *s;

		if (!charValue)
			break;

		if (!(charValue == ' ' || charValue & 0x80) ){
			// characters are 8x8, 16 of them fit in a row
			int charArea = character * 8;

			for (charPixelColumn = 0; charPixelColumn < 8; charPixelColumn++)
				screen_buffer[row + charArea + charPixelColumn] |= font[charValue * 8 + charPixelColumn];
				// each cad goes up to 32 bits, 4 sets of 8 (columns), go through these columns
				// then OR with the font value (character bits)
		}
	}
}

void set_pixel(int x, int y) {
	screen_buffer[( y / 8 ) * 128 + x] |= 1 << y % 8;
}

void clr_pixel(int x, int y) {
	screen_buffer[( y / 8 ) * 128 + x] &= ~(1 << y % 8);
}
