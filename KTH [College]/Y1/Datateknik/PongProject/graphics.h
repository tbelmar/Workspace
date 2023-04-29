#ifndef GRAPHICS_H
#define GRAPHICS_H

#include <stdint.h>
#include "paddleball.h"


void delay(int cycle);

void spiSetup();

uint8_t spi_send_recv(uint8_t data);

void drawPaddle(Paddle paddle);

void displayCords(int x, int y);

void drawBall(Ball thisBall);

void drawPoints(Paddle player_1, Paddle player_2);

void draw(Paddle player_1, Paddle player_2, Ball thisBall);



#endif

