package Backend;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class Unit extends Tile {

    protected Resource health;
    protected int attack;
    protected int defence;
    protected String name;

    protected TileInCoordinateProvider tileInCoordinateProvider;
    protected DeathCallBack deathCallBack;
    protected MessageCallBack messageCallBack;
    protected final Random rnd = new Random();

    protected Unit(char tile, String name, int healthPoolSize, int attack,int defence) {
        super(tile);
        health = new Resource(healthPoolSize);
        this.attack = attack;
        this.defence = defence;
        this.name = name;
    }


    public void setTileInCoordinateProvider(TileInCoordinateProvider tileInCoordinateProvider) {
        this.tileInCoordinateProvider = tileInCoordinateProvider;
    }

    public int getHealthPool() {
        return health.getCapacity();
    }

    public void setHealthPool(int newPool) {
        health.setCapacity(newPool);
    }

    public int getHealthAmount() {
        return health.amount;
    }

    public void setHealthAmount(int healthAmount) {
        health.setAmount(healthAmount);
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setDeathCallBack(DeathCallBack deathCallBack) {
        this.deathCallBack = deathCallBack;
    }

    public abstract void onDeath();


    public void setMessageCallBack(MessageCallBack messageCallBack) {
        this.messageCallBack = messageCallBack;
    }

    public boolean isAlive() {
        return !health.isEmpty();
    }

    public void reduceHealth(int amount) {
        if (amount>0) {
            health.reduce(amount);
            if (!isAlive()) {
                onDeath();
            }
        }
    }

    public void visit(Wall wall) {
    }

    public void visit(Empty empty) {
        switchCoordinate(empty);
    }

    public abstract void visit(Enemy enemy);

    public abstract void visit(Player player);

    protected void switchCoordinate(Tile tile) {
        Coordinate tmp = new Coordinate(tile.coordinate);
        Tile tileToSwitchWith = tileInCoordinateProvider.provide(tile.getCoordinate().getX(),tile.getCoordinate().getY());
        tileToSwitchWith.setCoordinate(this.getCoordinate());
        this.setCoordinate(tmp);
    }

    protected int attack() {
        return rnd.nextInt(attack);
    }

    protected int defend() {
        return rnd.nextInt(defence);
    }

    public void fight(Unit defender) {
        Unit attacker = this;
        int attack = attacker.attack();
        int defence = defender.defend();
        if (defence < attack) {
            defender.reduceHealth(attack - defence);
            messageCallBack.send(String.format("%s rolled %d attackPoint \n%s rolled %d defensePoint and lost %d lifePoint %d/%d",attacker.getName(),attack,defender.getName(),defence,attack-defence,defender.getHealthAmount(),defender.getHealthPool()));
        }else {
           messageCallBack.send(String.format("%s rolled %d attackPoint \n%s rolled %d defensePoint",attacker.getName(),attack,defender.getName(),defence));
        }
    }

    public abstract void play();

    public String getName() {
        return name;
    }

    protected void move(char direction){
        switch (direction){
            case 'd': //right
                tileInCoordinateProvider.provide(getCoordinate().getX(),getCoordinate().getY()+1).accept(this);

                break;
            case 'a': //left
                tileInCoordinateProvider.provide(getCoordinate().getX(),getCoordinate().getY()-1).accept(this);
                break;

            case 'w': //up
                tileInCoordinateProvider.provide(getCoordinate().getX()-1,getCoordinate().getY()).accept(this);
                break;

            case 's': //down
                tileInCoordinateProvider.provide(getCoordinate().getX()+1,getCoordinate().getY()).accept(this);
                break;


            case 'q':
                break;

            default:
                throw new IllegalArgumentException(String.format("Unit.move cannot git in input %s",direction));
        }
    }
}
