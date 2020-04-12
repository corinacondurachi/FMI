# AI

## 1.  A* algorithm


### 1. [Stacks Problem](https://github.com/corinacondurachi/FMI/blob/master/AI/A_star/blocuri.py)
We are given N stacks and M cubes, placed one over another in different stacks. We have to provide a solution to reach a Goal Configuration using A* algorithm, being allowed to move only the cubes on top. 

### 2. [8-Puzzle](https://github.com/corinacondurachi/FMI/blob/master/AI/A_star/8puzzle.py)
8-Puzzle is a popular puzzle that consists of N tiles where N can be 8, 15, 24 and so on. The puzzle is divided into sqrt(N+1) rows and sqrt(N+1) columns. The puzzle can be solved by moving the tiles one by one in the single empty space and thus achieving the Goal configuration.

### 3.  [Missionaries and cannibals problem](https://github.com/corinacondurachi/FMI/blob/master/AI/A_star/cautare_drum.py)
In the missionaries and cannibals problem, N missionaries and N cannibals must cross a river using a boat which can carry at most M people, under the constraint that, for both banks, if there are missionaries present on the bank, they cannot be outnumbered by cannibals (if they were, the cannibals would eat the missionaries). The boat cannot cross the river by itself with no people on board.

### 4. [Searching for a way from a source to a destination](https://github.com/corinacondurachi/FMI/blob/master/AI/A_star/cautare_drum.py)
We are given a set of names and we have to send a ticket from a source to a destination considering some limitations. Some desks are empty and some children are angry with each other. The message can be passed to the next column only through last two rows.

## 1. Min-Max algorithm and Alpha-Beta Pruning

### 1. [Tic-tac-toe](https://github.com/corinacondurachi/FMI/blob/master/AI/MinMax%20-%20Alpha%20Beta%20Pruning/tic_tac_toe.py)
Tic-tac-toe, noughts and crosses, or Xs and Os is a paper-and-pencil game for two players, X and O, who take turns marking the spaces in a 3×3 grid. The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row is the winner. This is an implementation of a Tic-Tac-Toe solver using the Minimax Algorithm and also Alpha-Beta pruning.

### 2. [Connected-Four](https://github.com/corinacondurachi/FMI/blob/master/AI/MinMax%20-%20Alpha%20Beta%20Pruning/connected4.py)
Connect Four s a two-player connection board game in which the players first choose a color and then take turns dropping one colored disc from the top into a seven-column, six-row vertically suspended grid. The pieces fall straight down, occupying the lowest available space within the column. The objective of the game is to be the first to form a horizontal, vertical, or diagonal line of four of one's own discs. Connect Four is a solved game. The first player can always win by playing the right moves. This is an implementation of a Connected-Four solver using the Minimax Algorithm and also Alpha-Beta pruning.

### 3. [Reversi](https://github.com/corinacondurachi/FMI/blob/master/AI/MinMax%20-%20Alpha%20Beta%20Pruning/Reversi.py)
Reversi is a strategy board game for two players, played on an 8×8 uncheckered board. Players take turns placing disks on the board with their assigned color facing up. During a play, any disks of the opponent's color that are in a straight line and bounded by the disk just placed and another disk of the current player's color are turned over to the current player's color. The object of the game is to have the majority of disks turned to display your color when the last playable empty square is filled. This is an implementation of a Reversi solver using the Minimax Algorithm and also Alpha-Beta pruning.


### 4. [Bomberman](https://github.com/corinacondurachi/FMI/blob/master/AI/MinMax%20-%20Alpha%20Beta%20Pruning/Bomberman.py)
Bomberman is a strategic, maze-based video game franchise originally developed by Hudson Soft and currently owned by Konami. This is an implementation of a Bomberman solver using the Minimax Algorithm and also Alpha-Beta pruning.

