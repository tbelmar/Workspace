// Written by: A Hammarstrand, H Hagberg

#include "drivers/OLED_I2C.h"
#include "drivers/OLED_SPI.h"
#include "data.h"
#include "input.h"
#include <stdint.h>

void fourlines(char r1[], char r2[], char r3[], char r4[]) {
    buffer_clear();

    load_string(0, r1);
    load_string(1, r2);
    load_string(2, r3);
    load_string(3, r4);

}

void threelines_and_menu(char r1[], char r2[], char r3[]) {
    buffer_clear();

    load_string(0, r1);
    load_string(1, r2);
    load_string(2, r3);

    load_image(image_menu);
}

void intro(void) {
    buffer_clear();
    load_image(image_intro);
    display_write();
}

void dialogue_control(void) {
    buffer_clear();
    load_image(image_dialogue_controls);
    display_write();
}

void outro(void) {
  buffer_clear();
  load_image(image_outro);
  display_write();
}
