# Chess Question

## Prerequisite
* Java 14
* [Gadle 6.3+](https://gradle.org/install/)

## Abstract
You have been provided with a third-party library `ChessLib` which calculates the legal moves a knight can make given a position on an ![8\times8](https://latex.codecogs.com/svg.latex?8%5Ctimes8) board. The library has been used to create a program which moves a knight randomly around a board, given an initial starting position and a total number of moves to make.

## Problem
Extend this program to set up an ![8\times8](https://latex.codecogs.com/svg.latex?8%5Ctimes8) square game board containing several different pieces in predefined positions.
For each move of the game, the program will choose a piece at random, and move it to a randomly selected valid position.

You are not allowed to change any of the `ChessLib` code.
 
Extend the program as required. 
Use Object Oriented Design and Modeling appropriately for extensibility.

## Game Rules
* Only one piece can occupy any position on the board at a given time.
* All pieces can “jump” any occupied position.

*__Note__*: Although the game bears a striking resemblance to Chess, this is entirely coincidental. Do not assume other chess rules apply.

## Game Pieces to support:
* Knight - Moves as implemented by ChessLib
* Bishop - Moves diagonally, any distance within board boundaries
* Queen – Moves diagonally, horizontally or vertically, any distance within board boundaries

## What you should do?
* Implement your business logic into the file `src/main/java/chessGame/ComplexGame.java`. If you want to add some auxiliary classes, please insert into the package `chessGame`.
* Write your unit tests into the folder `src/test/java/chessGame`.
* If you want to test your application, you can modify `src/main/java/chessGame/App.java` to print the output.

## About Gradle
Gradle is a build tool for compiling & testing the application. The following functions may be helpful to you.

**gradle check**: Run all unit tests.

**gradle run**: Run the main application.

**gradle tasks**: Print all the supported tasks.