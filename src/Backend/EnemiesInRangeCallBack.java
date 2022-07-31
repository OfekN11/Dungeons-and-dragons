package Backend;

import java.util.List;

public interface EnemiesInRangeCallBack {
    public List<Enemy> enemiesInRange(Coordinate coordinate, int range);
}
