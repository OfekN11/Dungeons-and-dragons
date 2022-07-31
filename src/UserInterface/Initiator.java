package UserInterface;

import Backend.Player;
import Backend.TileFactory;

import java.io.File;
import java.util.*;

public class Initiator {
    public static void main(String[] args) {
        InputReceiver receiver = new InputReceiver();
        // System.out.println("Please enter the path to the level directory");
        // String dirPath = receiver.receivePath();
        String dirPath = "C:\\Users\\Ofek\\Desktop\\Univarsity\\Object_Oriented\\game_example\\levels_dir\\levels_dir";
        TileFactory tileFactory = new TileFactory();
        int playerChoice = receiver.chooseCharacter(tileFactory.listPlayers());
        Player player = tileFactory.producePlayer(playerChoice, () -> receiver.chooseAction());
        File levelFolder = new File(dirPath);
        File[] levelFiles = levelFolder.listFiles();
        GameManager gameManager = new GameManager(levelFiles, player);
        gameManager.startGame();
    }


}
