package Backend;

public class Wall extends Tile{

    public Wall(){
        super('#');
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public void accept(Unit unit) {
        //do nothing
    }
}
