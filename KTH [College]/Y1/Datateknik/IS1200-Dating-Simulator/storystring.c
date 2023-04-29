/*  
    Written by: H Hagberg
    Assistence: A Hammarstrand
*/
#include <stdint.h>
#include "sample.h"
#include "input.h"
#include "minigame/game.h"
#include "minigame/levels.h"
#include "drivers/OLED_SPI.h"
#include "drivers/OLED_I2C.h"
#include "data.h"

position player;
position goal;

int readread = 0;

int love = 0;
int friendly = 0;
int neutral = 0;

int btn2add = 0;
int btn3add = 0;
int btn4add = 0;

int monologuetoggle = 1;
int currentmonologue = 0; //currentmonologue: 3 = Neutral, 4 = Friendly, 5 = Love
int submonologue = 0;

int nextconv = 0;
int currentbtn = 4;
int currentstage = 1;

void monologue()
{
  if (currentmonologue == 0)
  {
    if (submonologue == 0)
    {
      fourlines(
          "Oh, Hello!",
          "Are you starting",
          "this math course",
          "as well?");
    }
    if (submonologue == 1)
    {
      currentmonologue++;
      monologuetoggle = 0;
      submonologue = 0;
    }
  }
  if (currentmonologue == 1)
  {
    if (submonologue == 0)
    {
      fourlines(
          "\"Meet me at the",
          "cafe once you're",
          "finished today.\"",
          "");
    }
    if (submonologue == 1)
    {
      fourlines(
          "The day flies by",
          "as you attend",
          "classes filled",
          "with knowledge.");
    }
    if (submonologue == 2)
    {
      fourlines(
          "As you finish ",
          "your last lesson",
          "for the day you",
          "remember...");
    }
    if (submonologue == 3)
    {
      fourlines(
          "You are late to",
          "the cafe! So",
          "you hurry out",
          "the building...");
    }
    if (submonologue == 4)
    {
      load_image(image_minigame_controls);
    }
    if (submonologue == 5)
    {
      goal.x = 120;
      goal.y = 15;

      player.x = 0;
      player.y = 0;

      init(level1, player, goal);
      game();
      submonologue++;
    }
    if (submonologue == 6)
    {
      fourlines(
          "Hi, you!",
          "Ready to get",
          "started?",
          "");
    }
    if (submonologue == 7)
    {

      currentmonologue++;
      monologuetoggle = 0;
      submonologue = 0;
      currentstage++;
    }
  }
  if (currentmonologue == 2)
  {
    if (submonologue == 0)
    {
      fourlines(
          "Both of you sit",
          "and run through",
          "the papers.",
          "");
    }
    if (submonologue == 1)
    {
      fourlines(
          "Answering the",
          "questions while",
          "the cafe buzz",
          "around you two.");
    }
    if (submonologue == 2)
    {
      fourlines(
          "You sit and work",
          "in full focus",
          "when you realize",
          "the time...");
    }
    if (submonologue == 3)
    {
      fourlines(
          "It is getting ",
          "late, what are",
          "you doing later",
          "this evening?");
    }
    if (submonologue == 4)
    {
      currentmonologue++;
      monologuetoggle = 0;
      submonologue = 0;
      currentstage++;
    }
  }
  if (currentmonologue == 3)
  { //NEUTRAL
    OLED_display_image(image_anime_neutral);
    if (submonologue == 0)
    {
      fourlines(
          "NEUTRAL ENDING",
          "",
          "",
          "");
    }
    if (submonologue == 1)
    {
      fourlines(
          "*She faces you*",
          "",
          "",
          "");
    }
    if (submonologue == 2)
    {
      fourlines(
          "Maybe we'll see",
          "eachother in",
          "the corridors.",
          "");
    }
    if (submonologue == 3)
    {
      fourlines(
          "See you.",
          "",
          "",
          "");
    }
    if (submonologue == 4)
    {
      load_image(image_credits);
      display_write();
      while (1)
        ;
    }
  }
  if (currentmonologue == 4)
  { //FRIENDLY
    OLED_display_image(image_anime_friendly);
    if (submonologue == 0)
    {
      fourlines(
          "FRIENDLY ENDING",
          "",
          "",
          "");
    }
    if (submonologue == 1)
    {
      fourlines(
          "*She faces you*",
          "",
          "",
          "");
    }
    if (submonologue == 2)
    {
      fourlines(
          "You are a great",
          "friend! Me and",
          "my friends are",
          "going to a...");
    }
    if (submonologue == 3)
    {
      fourlines(
          "Party tonight",
          "and I want you",
          "to join us!",
          "");
    }
    if (submonologue == 4)
    {
      fourlines(
          "We are going",
          "to have so",
          "much fun!",
          "");
    }
    if (submonologue == 5)
    {
      load_image(image_credits);
      display_write();
      while (1)
      {
      }
    }
  }
  if (currentmonologue == 5)
  { //LOVE
    OLED_display_image(image_anime_loving);
    if (submonologue == 0)
    {
      fourlines(
          "LOVE ENDING",
          "",
          "",
          "");
    }
    if (submonologue == 1)
    {
      fourlines(
          "*She faces you*",
          "",
          "",
          "");
    }
    if (submonologue == 2)
    {
      fourlines(
          "You've really",
          "grown on me...",
          "",
          "");
    }
    if (submonologue == 3)
    {
      fourlines(
          "You make me feel",
          "very special and",
          "I was wondering",
          "if you want...");
    }
    if (submonologue == 4)
    {
      fourlines(
          "to be with me?",
          "",
          "",
          "");
    }
    if (submonologue == 5)
    {
      fourlines(
          "We could go on",
          "a date tomorrow!",
          "",
          "");
    }
    if (submonologue == 6)
    {
      fourlines(
          "I will see you",
          "then <3",
          "",
          "");
    }
    if (submonologue == 7)
    {
      load_image(image_credits);
      display_write();
      while (1)
      {
      }
    }
  }
  if (currentmonologue == 6)
  { //SECRET
    OLED_display_image(image_anime_angry);
    if (submonologue == 0)
    {
      fourlines(
          "SECRET ENDING",
          "",
          "",
          "");
    }
    if (submonologue == 1)
    {
      fourlines(
          "*She faces you*",
          "",
          "",
          "");
    }
    if (submonologue == 2)
    {
      fourlines(
          "You",
          "are not",
          "paying",
          "ATTENTION!!!!!!!");
    }
    if (submonologue == 3)
    {
      fourlines(
          "You are just",
          "mindlessly",
          "doing what",
          "I say???");
    }
    if (submonologue == 4)
    {
      fourlines(
          "There is",
          "something very",
          "wrong with you",
          "");
    }
    if (submonologue == 5)
    {
      fourlines(
          "Are you on drugs",
          "...anyways",
          "",
          "");
    }
    if (submonologue == 6)
    {
      fourlines(
          "I never want",
          "to see you",
          "ever again!",
          "");
    }
    if (submonologue == 7)
    {
      load_image(image_credits);
      display_write();
      while (1)
      {
      }
    }
  }
}

void story()
{
  if (nextconv == 1)
  {
    if (currentstage == 6)
    {

      if (btn4add == 5 || btn3add == 5 || btn2add == 5)
      {
        currentmonologue = 6;
      }
      else
      {

        if (neutral > friendly && neutral > love)
        {
          currentmonologue = 3;
        }
        else if (friendly > neutral && friendly > love)
        {
          currentmonologue = 4;
        }
        else if (love > neutral && love > friendly)
        {
          currentmonologue = 5;
        }
        else if (neutral == friendly || neutral == love)
        {
          currentmonologue = 3;
        }
        else if (friendly == love)
        {
          currentmonologue = 4;
        }
      }
      monologuetoggle = 1;
    }
    if (currentbtn == 2)
    {
      if (currentstage == 1)
      {
        fourlines(
            "I wouldn't mind",
            "being stuck with",
            "you if you are",
            "good with math.");
        if (readread)
        {
          love++;
          btn2add++;
          readread = 0;
        }
        OLED_display_image(image_anime_loving);
      }
      if (currentstage == 2)
      {
        fourlines(
            "Hmmm, maybe we",
            "can study after",
            "class? Two is",
            "better than one.");
        if (readread)
        {
          friendly++;
          btn2add++;
          readread = 0;
        }
        OLED_display_image(image_anime_friendly);
      }
      if (currentstage == 3)
      {
        fourlines(
            "Right, maybe.",
            "Let's head into",
            "class.",
            "");
        display_write();
        OLED_display_image(image_anime_neutral);
        while (!getbtns() == 1)
          ;
        while (getbtns() == 1)
          ;
        neutral++;
        btn2add++;
        monologuetoggle = 1;
        nextconv = 0;
      }
      if (currentstage == 4)
      {
        fourlines(
            "Hell yeah! This",
            "is going to be a",
            "great studying",
            "session!");
        display_write();
        OLED_display_image(image_anime_friendly);
        while (!getbtns() == 1)
          ;
        while (getbtns() == 1)
          ;
        friendly++;
        btn2add++;
        monologuetoggle = 1;
        nextconv = 0;
      }
      if (currentstage == 5)
      {
        fourlines(
            "I wouldn't mind",
            "spending more",
            "time with you.",
            "what do you say?");
        if (readread)
        {
          love++;
          btn2add++;
          readread = 0;
        }
        OLED_display_image(image_anime_loving);
      }
    }
    if (currentbtn == 3)
    {
      if (currentstage == 1)
      {
        fourlines(
            "Yeah lets sit",
            "together!",
            "Are you any good",
            "with math?");
        if (readread)
        {
          friendly++;
          btn3add++;
          readread = 0;
        }
        OLED_display_image(image_anime_friendly);
      }
      if (currentstage == 2)
      {
        fourlines(
            "I feel behind on",
            "math in general",
            "maybe we can do",
            "a study group?");
        if (readread)
        {
          neutral++;
          btn3add++;
          readread = 0;
        }
        OLED_display_image(image_anime_neutral);
      }
      if (currentstage == 3)
      {
        fourlines(
            "Come on! Let's",
            "sit together in",
            "class!",
            "");
        display_write();
        OLED_display_image(image_anime_loving);
        while (!getbtns() == 1)
          ;
        while (getbtns() == 1)
          ;
        love++;
        btn3add++;
        monologuetoggle = 1;
        nextconv = 0;
      }
      if (currentstage == 4)
      {
        fourlines(
            "Mmmmmmm",
            "I'd always study",
            "if I got to meet",
            "you.");
        display_write();
        OLED_display_image(image_anime_loving);
        while (!getbtns() == 1)
          ;
        while (getbtns() == 1)
          ;
        love++;
        btn3add++;
        monologuetoggle = 1;
        nextconv = 0;
      }
      if (currentstage == 5)
      {
        fourlines(
            "Maybe we could",
            "figure something",
            "out, what do you",
            "say?");
        if (readread)
        {
          readread = 0;
          friendly++;
          btn3add++;
        }
        OLED_display_image(image_anime_friendly);
      }
    }
    if (currentbtn == 4)
    {
      if (currentstage == 1)
      {
        fourlines(
            "Right, tell me.",
            "Are you any good",
            "with math?",
            "");
        if (readread)
        {
          neutral++;
          btn4add++;
          readread = 0;
        }
        OLED_display_image(image_anime_neutral);
      }
      if (currentstage == 2)
      {
        fourlines(
            "I feel the same.",
            "We should totaly",
            "study together",
            "after class!");
        if (readread)
        {
          readread = 0;
          love++;
          btn4add++;
        }
        OLED_display_image(image_anime_loving);
      }
      if (currentstage == 3)
      {
        fourlines(
            "For sure! Come",
            "our lesson is",
            "about to start!",
            "");
        display_write();
        OLED_display_image(image_anime_friendly);
        while (!getbtns() == 1)
          ;
        while (getbtns() == 1)
          ;
        friendly++;
        btn4add++;
        monologuetoggle = 1;
        nextconv = 0;
      }
      if (currentstage == 4)
      {
        fourlines(
            "You're right.",
            "let's get on",
            "with the work.",
            "");
        display_write();
        OLED_display_image(image_anime_neutral);
        while (!getbtns() == 1)
          ;
        while (getbtns() == 1)
          ;
        neutral++;
        btn4add++;
        monologuetoggle = 1;
        nextconv = 0;
      }
      if (currentstage == 5)
      {
        fourlines(
            "Don't you want",
            "to do something",
            "else later",
            "this evening?");
        if (readread)
        {
          readread = 0;
          neutral++;
          btn4add++;
        }
        OLED_display_image(image_anime_neutral);
      }
    }
  }

  if (nextconv == 0)
  {
    readread = 1;
    if (currentbtn == 2)
    {
      if (currentstage == 1)
      {
        threelines_and_menu(
            "Yeah, I guess",
            "we'll be stuck",
            "together.");
      }
      if (currentstage == 2)
      {
        threelines_and_menu(
            "Well, I am kinda",
            "getting the hang",
            "of it.");
      }
      if (currentstage == 3)
      {
        threelines_and_menu(
            "Maybe we'll",
            "start one",
            "someday.");
      }
      if (currentstage == 4)
      {
        threelines_and_menu(
            "Let's study like",
            "never before!",
            "");
      }
      if (currentstage == 5)
      {
        threelines_and_menu(
            "If you are free",
            "we could hang",
            "some more.");
      }
      if (currentstage == 6)
      {
        threelines_and_menu(
            "We should def.",
            "do something",
            "tonight!");
      }
    }
    if (currentbtn == 3)
    {
      if (currentstage == 1)
      {
        threelines_and_menu(
            "I am! Wanna grab",
            "a seat in the",
            "classroom?");
      }
      if (currentstage == 2)
      {
        threelines_and_menu(
            "I mean, I am ok",
            "at it i guess.",
            "");
      }
      if (currentstage == 3)
      {
        threelines_and_menu(
            "I won't survive",
            "this course any",
            "other way.");
      }
      if (currentstage == 4)
      {
        threelines_and_menu(
            "I am ready now",
            "that I am with",
            "you.");
      }
      if (currentstage == 5)
      {
        threelines_and_menu(
            "Nothing planned",
            "I'd love to do",
            "something though");
      }
      if (currentstage == 6)
      {
        threelines_and_menu(
            "Nah maybe",
            "not sure.",
            "");
      }
    }
    if (currentbtn == 4)
    {
      if (currentstage == 1)
      {
        threelines_and_menu(
            "Hi, yeah I will",
            "be reading this",
            "course.");
      }
      if (currentstage == 2)
      {
        threelines_and_menu(
            "Maybe with you",
            "around I'll get",
            "the hang of it.");
      }
      if (currentstage == 3)
      {
        threelines_and_menu(
            "Together we will",
            "ace the course",
            "for sure!");
      }
      if (currentstage == 4)
      {
        threelines_and_menu(
            "Let's just dish",
            "this days class",
            "off");
      }
      if (currentstage == 5)
      {
        threelines_and_menu(
            "I don't know, I",
            "might go home,",
            "watch a movie.");
      }
      if (currentstage == 6)
      {
        threelines_and_menu(
            "I'd love to!",
            "",
            "");
      }
    }
  }
}

void selectbtn(int shift)
{
  shift = (shift * 28) + 5;
  clr_pixel(shift, 27);
  clr_pixel(shift, 28);
  clr_pixel(shift, 29);
  clr_pixel(shift + 1, 27);
  clr_pixel(shift + 1, 28);
  clr_pixel(shift + 1, 29);
  clr_pixel(shift + 2, 27);
  clr_pixel(shift + 2, 28);
  clr_pixel(shift + 2, 29);
}

void option_screen(void)
{
  if (getbtns() == 1)
  {
    if (monologuetoggle)
    {
      submonologue++;
    }
    else
    {
      if (nextconv == 1)
      {
        currentstage++;
        nextconv = 0;
      }
      else
      {
        nextconv = 1;
      }
    }
  }
  if (getbtns() == 2)
  {
    if (monologuetoggle)
    {
    }
    else
    {
      if (nextconv == 1)
      {
      }
      else
      {
        currentbtn = 2;
      }
    }
  }
  if (getbtns() == 4)
  {
    if (monologuetoggle)
    {
    }
    else
    {
      if (nextconv == 1)
      {
      }
      else
      {
        currentbtn = 3;
      }
    }
  }
  if (getbtns() == 8)
  {
    if (monologuetoggle)
    {
    }
    else
    {
      if (nextconv == 1)
      {
      }
      else
      {
        currentbtn = 4;
      }
    }
  }

  while (getbtns());
  buffer_clear();

  if (monologuetoggle)
  {
    monologue();
  }
  else
  {
    if (nextconv)
    {
      story();
    }
    else
    {
      if (currentbtn == 2)
      {
        story();
        selectbtn(2);
      }
      if (currentbtn == 3)
      {
        story();
        selectbtn(1);
      }
      if (currentbtn == 4)
      {
        story();
        selectbtn(0);
      }
    }
  }
}
