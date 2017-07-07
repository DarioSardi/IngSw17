# GROUP GC_43 "LORENZO IL MAGNIFICO" PROJECT

This repository contains our group software engineering project about  "Lorenzo il Magnifico" game.

## Team

Team member 1: Francesco Saverio Varini, 10482010, github: frenzDataCode

Team member 2: Dario Sardi, 10456269 github: Dario Sardi

Team member 3: Samuele Sapuppo, 10454582 github: Samuele Sapuppo

### How to play the game

1) Run the server (inside Controller package). 

2) Run Clients and select the connection configuration: manual /socket/rmi. In the first case, that is to say, "manual", the player should be aware of the server IP address and corresponding port. The other two choice will automatically set socket IP to "localhost" and port 7777;

3) Once input the choice as displayed, it will be asked to select any Username for player X;

4) Then main menu will be displayed as follows:

MAIN MENU:
1.Create new lobby!
2.Join a lobby
3.Exit the game

As this menu suggests, our game has lobbies which allow to create muliple concurrent gameplay running on different threads.
It is possible for connected player to create a lobby and, right after creation, the player will be the administator of it, he will be able to choose how many players will be accepted in the game (totally 5) and he will decide if to play with game advanced rules. Once done, new lobby will be up and running, ready to accept joiners.
In case the number of players, established previously by the lobby  administrator, will be reached in the lobby, automatically a timer will start. After it  will finish counting, the game will start.

5) Good luck for the game!
