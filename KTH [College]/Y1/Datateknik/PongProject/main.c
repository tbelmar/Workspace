#include <stdio.h>
//#include <pic32mx>
#include "graphics.h"
#include "paddleball.h"
#include "tools.h"


#define POINTS_TO_WIN 5
#define PADDLE_LENGTH 12
#define SPEED 150
#define y_ceiling 30
#define x_border 120

Paddle player_1, player_2; //Declare two paddles, one for each player
Ball theBall;

void ballMovement() {
	// The balls x/y positions are manipulated by the set speed each execution //
	theBall.x = (theBall.x + theBall.speedX);
	theBall.y = (theBall.y + theBall.speedY);

	//Bouncing on the ceiling //
	if (theBall.y >= y_ceiling) {

		theBall.y = y_ceiling;
		theBall.speedY = theBall.speedY * (-1);
	}
	else if (theBall.y <= 0) {

		theBall.y = 1;
		theBall.speedY = theBall.speedY * (-1);
	}

	//Bouncing on the borders
	if (theBall.x <= 0) {
		//Check if one player scored//
		if (theBall.y < player_1.y || theBall.y < (player_1.y - (PADDLE_LENGTH + 1))) {
			player_2.points++;
			}
		theBall.x = 0;
		theBall.speedX = (theBall.speedX * (-1));
	}
	
	if (theBall.x >= x_border) {
		//Check if the other players scored//
		if (theBall.y < player_2.y || theBall.y < (player_2.y - (PADDLE_LENGTH + 1))) {
			player_1.points++;
		}
		theBall.x = 120;
		theBall.speedX = (theBall.speedX * (-1));
	}
}


int main() {

	spiSetup(); //wtf even is this

	startGame();

	enableButton();







	return 0;
}


void startGame() {
	//Startup condition for the ball and paddles//

	player_1.y = 10;		//Places paddles on opposite ends of the X axis
	player_1.x = 1;

	player_2.y = 10;
	player_2.x = (x_border - 1);

	player_1.points = 0;
	player_2.points = 0;

	theBall.x = (x_border / 2);  //Should put it in the middle of the screen
	theBall.y = (y_ceiling / 2);
	theBall.speedX = 2;			//Double the speed, gives it a tanget of 2
	theBall.speedY = 1;

}



void paddleMovement() {
	 if ( 0) {
		//Need to get button inputs to make paddles move in y++/y--
	}
	if ( 0) {
		//player 1 paddles moves in y--
	}
	if ( 0) {
		//player 2 paddles moves in y++
	}
	if ( 0) {
		//player 2 paddles moves in y--
	}
}

