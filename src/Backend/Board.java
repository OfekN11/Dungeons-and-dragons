package Backend;
import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private List<Tile> tiles;
    private List<Enemy> enemies;
    private Player player;
    private int cols;
    private int rows;

    private String callBackStrings;
    public Board(int cols,int rows){
        this.cols=cols;
        this.rows=rows;
        this.enemies = new ArrayList<Enemy>();
        this.tiles = new ArrayList<Tile>();
        callBackStrings = "";
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
    public void addEnemy(Enemy e){
        enemies.add(e);
        tiles.add(e);
    }
    public List<Enemy> getEnemiesInRange(Coordinate coordinate,int range){
        List<Enemy>enemiesInRange = new LinkedList<>();
        for(Enemy e : enemies){
            if(coordinate.range(e.getCoordinate())<=range)
                enemiesInRange.add(e);
        }
        return enemiesInRange;

    }
    @Override
    public String toString() {
        String output= callBackStrings + "\\n" + tiles.stream().sorted().map((tile)->tile.toString() + (tile.getCoordinate().getY() != cols-1 ? "" : "\n")).collect(Collectors.joining(""));
        resetCallBackString();
        return output;
    }
    public Tile getTile(int x,int y ){
        for (Tile tile : tiles){
            if (tile.getCoordinate().getX()==x & tile.getCoordinate().getY()==y) {
                return tile;
            }
        }
        return null;
    }

    public void resetCallBackString() {
        callBackStrings = "";
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public void addTile(Tile tile){
        tiles.add(tile);
    }


    public void enemyDied(Enemy e) {
        enemies.remove(e);
        tiles.remove(e);
        Empty empty = new Empty();
        empty.initialize(e.coordinate);
        addTile(empty);
    }

    public Player playerInRang(Coordinate coordinate, int range) {
        return coordinate.range(player.getCoordinate())<=range ? player : null;
    }

    public void addCallBackString(String msg){
        callBackStrings = callBackStrings +"\n" + msg;
    }
}
