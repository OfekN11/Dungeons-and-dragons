package Backend;

import java.util.zip.CheckedOutputStream;

public class Coordinate implements Comparable<Coordinate> {
    private int x;
    private int y;
    public Coordinate(int x, int y) {
        this.x=x;
        this.y=y;
    }
    public Coordinate(Coordinate coordinate){
        x = coordinate.getX();
        y = coordinate.getY();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double range(Coordinate coordinate){
        return Math.abs(Math.sqrt(Math.pow(coordinate.getY()-y,2)+Math.pow(coordinate.getX()-x,2)));
    }
    
    @Override
    public int compareTo(Coordinate coordinate) {
        int output =0;
        if(x!=coordinate.getX()){
            output = x-(coordinate).getX()>0 ? 1 :-1;
        }
        else if (y!=coordinate.getY()){
            output = y-(coordinate).getY()>0 ? 1 :-1;
        }
        return output;
    }
}

