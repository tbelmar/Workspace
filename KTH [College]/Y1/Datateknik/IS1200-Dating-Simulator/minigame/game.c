/* 
    Written by: A Hammarstrand
    Assistence: H Hagberg
*/
#include <stdint.h>
#include "../drivers/OLED_SPI.h"
#include "../drivers/input.h"
#include "levels.h"
#include "game.h"

#define P_WIDTH 4
#define P_HEIGHT 4

const uint8_t* _level;
position g;
position p;
uint8_t exit = 0;


void init(const uint8_t* level, position player, const position goal) {
    _level = level;
    p = player;
    g = goal;
}

void draw(void) {
    // Reset buffer
    buffer_clear();

    // Draw level
    load_image(_level);

    int x, y;
    
    // Draw player
    for (y = 0; y < P_HEIGHT; y++)
        for (x = 0; x < P_WIDTH; x++)
            set_pixel(p.x + x, p.y + y);

    // Draw goal
    for (y = -1; y < 1 + P_HEIGHT; y++){
        for (x = -1; x < 1 + P_WIDTH; x++) {
            if (y > -1 && y < P_HEIGHT &&
                x > -1 && x < P_WIDTH)
                continue;
            
            set_pixel(g.x + x, g.y + y);
        }
    }

    // Write buffer
    display_write();
}

/* 
    Returns false if on edge of structure or screen.
    Returns true  if on walkable area.
*/
int checkbounds(int x, int y) {
    if (x < 0 || x > 128 - P_WIDTH || y < 0 || y > 32 - P_HEIGHT) return 0;
    
    uint8_t column = _level[(y / 8) * 128 + x];
    return (column & (1 << (y % 8)));
}

void up() { 
    if (checkbounds(p.x, p.y - 1)) p.y -= P_HEIGHT + 1;
}

void down() {
    if (checkbounds(p.x, p.y + P_HEIGHT)) p.y += P_HEIGHT + 1;
}

void left() {
    if (checkbounds(p.x - 1, p.y)) p.x -= P_WIDTH + 1;
}

void right() {
    if (checkbounds(p.x + P_WIDTH, p.y)) p.x += P_WIDTH + 1;
}

void logic(void) {
    switch (BTNA) {
        case 0x1: right();
            break;
        case 0x2: left();
            break;
        case 0x4: down();
            break;
        case 0x8: up();
            break;
    }
    while (BTNA);
    

    if (p.x == g.x && p.y == g.y)
        exit = 1;
}

void game(void) {
    while (!exit) {
        logic();
        draw();
    }
}