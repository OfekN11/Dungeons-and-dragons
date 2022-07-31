package UserInterface;

import Backend.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FileParser {
    public GameLevel parseLevel(File levelFile, Player player) {
        List<String> content = null;
        try {
            content = Files.readAllLines(levelFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        TileFactory tileFactory = new TileFactory();
        int rows = content.size();
        int cols = content.get(0).length();
        Board board = new Board(cols,rows);
        GameLevel gameLevel = new GameLevel(board);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Coordinate p = new Coordinate(i, j);
                char c = content.get(i).charAt(j);
                if (c == '.')
                    board.addTile(tileFactory.produceEmpty(p));
                else if (c == '#')
                    board.addTile(tileFactory.produceWall(p));
                else if (c == '@') {
                    player.initializeToBoard(p, (coordinate, range) -> board.getEnemiesInRange(coordinate, range),(x, y) -> board.getTile(x,y),(msg)->board.addCallBackString(msg));
                    board.addTile(player);
                    board.setPlayer(player);
                } else {
                    Enemy enemy = tileFactory.produceEnemy(c, p, (msg)->board.addCallBackString(msg),(coordinate,range)->board.playerInRang(coordinate,range), board::getTile);
                    enemy.setDeathCallBack(() -> board.enemyDied(enemy));
                    board.addEnemy(enemy);
                }
            }
        }

        return gameLevel;
    }
}







