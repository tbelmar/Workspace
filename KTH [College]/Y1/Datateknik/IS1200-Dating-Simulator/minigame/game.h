/* 
    Written by: A Hammarstrand
    Assistence: H Hagberg
*/

#ifndef GAME_h
#define GAME_h

typedef struct position {
    int x;
    int y;
} position;

void init(const uint8_t* level, position player, const position goal);
void game(void);


#endif