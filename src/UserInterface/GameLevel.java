package UserInterface;


import Backend.Board;
import Backend.Enemy;
import Backend.Player;

import java.util.List;

public class GameLevel {
    private Board gameBoard;
    public GameLevel(Board gameBoard){
        this.gameBoard = gameBoard;

    }
    public void round()
    {
      gameBoard.getPlayer().play();
      for(Enemy enemy : gameBoard.getEnemies()){
          enemy.play();
      }
    }



    @Override
    public String toString() {
        return String.format("%s\n%s", gameBoard, gameBoard.getPlayer().describe());
    }

    public boolean hasEnemy() {
        return !gameBoard.getEnemies().isEmpty();
    }
}
