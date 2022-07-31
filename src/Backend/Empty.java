package Backend;

public class Empty extends Tile {
    public Empty(){
        super('.');
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void accept(Unit unit) {
        swapPlace(unit);
    }

}
