package UserInterface;

import Backend.Player;

import java.io.File;

public class GameManager {
    private GameLevel gamelevel;
    private File[] levelFiles;
    private FileParser fileParser;
    Player player;
    private boolean gameOver;
    public GameManager(File[] levelFiles, Player player) {
        this.player = player;
        this.fileParser = new FileParser();
        this.levelFiles = levelFiles;
        gameOver = false;
    }
    public void startGame(){
        player.setDeathCallBack(()->gameOver=true);
        for (int i=0; i<levelFiles.length & !gameOver;i++){
            gamelevel = fileParser.parseLevel(levelFiles[i], player);
            while(!gameOver& gamelevel.hasEnemy())
            {
                System.out.println(gamelevel.toString());
                gamelevel.round();

            }
        }
        System.out.println(gamelevel.toString());
        if (player.isAlive()){
            System.out.println("congratulation you wan");
        }
        else {
            System.out.println("You died\nhopefully next time will be better for you");
        }
    }

}
