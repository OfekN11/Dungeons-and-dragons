package Backend;

public class Monster extends Enemy {
    public Monster(char tile,String name,int health,int attack, int defence,int expValue,int visionRange) {
        super(tile,name,health,attack,defence,expValue,visionRange);
    }

    @Override
    public void play() {
        Player player = playerInRangeCallBack.check(this.coordinate,visionRange);
        if (player != null){
            walkToPlayer(player);
        }
        else {
            walkRandomly();
        }
    }

    private void walkRandomly() {
        int direction = rnd.nextInt(4);
        switch (direction) {
            case 0 -> move('w');
            case 1 -> move('s');
            case 2 -> move('d');
            case 3 -> move('a');
        }
    }

    private void walkToPlayer(Player player) {
        int dy = getCoordinate().getX() - player.getCoordinate().getX();
        int dx = getCoordinate().getY() - player.getCoordinate().getY();
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0)
                move('a');
            else
                move('d');
        } else {
            if (dy > 0)
                move('w');
            else
                move('s');
        }


//        Coordinate playerCoordinate = player.getCoordinate();
//        if (playerCoordinate.getX()>getCoordinate().getX()){
//            move('d');
//        }
//        else if (playerCoordinate.getX()<getCoordinate().getX()){
//            move('a');
//        }
//        else if (playerCoordinate.getY()>getCoordinate().getY()){
//            move('w');
//        }
//        else {
//            move('s');
//        }
    }
}
