package Backend;

public abstract class Tile implements Comparable<Tile> {
    protected char tile;
    protected Coordinate coordinate;

    protected Tile(char tile){
        this.tile = tile;
    }
    public void initialize(Coordinate coordinate){
        this.coordinate = coordinate;
    }
 

    public char getTile() {
        return tile;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    protected void initiateTile(Coordinate coordinate) {
        this.coordinate = coordinate;
    }


    @Override
    public int compareTo(Tile tile) {
        return this.coordinate.compareTo(tile.coordinate);


    }

    public abstract void accept(Unit unit);

    @Override
    public String toString() {
        return String.valueOf(tile);
    }


    public void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    public void swapPlace(Tile tile){
        Coordinate forSwap = this.getCoordinate();
        this.setCoordinate(tile.getCoordinate());
        tile.setCoordinate(forSwap);
    }
}
