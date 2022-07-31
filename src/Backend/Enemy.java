package Backend;

public abstract class Enemy extends Unit{
    int expValue;
    int visionRange;
    PlayerInRangeCallBack playerInRangeCallBack;
    public Enemy(char tile, String name, int health, int attack, int defence, int expValue, int visionRange) {
        super(tile,name,health,attack,defence);
        this.expValue =expValue;
        this.visionRange = visionRange;
    }


    public void initialize(Coordinate position, MessageCallBack messageCallback, PlayerInRangeCallBack playerInRangeCallBack,TileInCoordinateProvider provider){
        super.initialize(position);
        setMessageCallBack(messageCallback);
        setTileInCoordinateProvider(provider);
        this.playerInRangeCallBack = playerInRangeCallBack;
    }

    public int getExpValue(){
        return this.expValue;
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }


    @Override
    public void onDeath() {
        deathCallBack.call();
    }

    @Override
    public void visit(Enemy enemy) {

    }

    @Override
    public void visit(Player player) {
        if (player.isAlive()) {
            fight(player);
        }
    }
}
