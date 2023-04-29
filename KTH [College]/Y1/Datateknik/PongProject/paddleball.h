#ifndef PADDLEBALL_H
#define PADDLEBALL_H

//declaration of ball and paddle with properties

typedef struct Ball {
	volatile int x, y, speedX, speedY;
} Ball; 

typedef struct Paddle {
	volatile int x, y, speed, points;
} Paddle;


#endif