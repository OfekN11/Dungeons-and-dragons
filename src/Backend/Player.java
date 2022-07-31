package Backend;

import java.util.List;

public abstract class Player extends Unit {
    public static final char playerTile = '@';
    protected static final int REQ_EXP = 50;
    protected static final int ATTACK_BONUS = 4;
    protected static final int DEFENSE_BONUS = 1;
    protected static final int HEALTH_BONUS = 10;

    protected int level;
    protected int experience;
    protected InputProvider inputProvider;
    protected EnemiesInRangeCallBack enemiesInRangeCallBack;

    protected Player(String name, int healthCapacity, int attack, int defense) {
        super(playerTile, name, healthCapacity, attack, defense);
        this.level = 1;
        this.experience = 0;
    }

    public void initialize(InputProvider inputProvider) {
        setMessageCallBack(messageCallBack);
        this.inputProvider = inputProvider;
    }

    protected List<Enemy> EnemiesInRangeCallBack(Coordinate coordinate, int range) {
        return enemiesInRangeCallBack.enemiesInRange(coordinate, range);//check if works
    }

    protected Enemy pickOneEnemy(int range) {
        List<Enemy> enemies = enemiesInRangeCallBack.enemiesInRange(this.coordinate, range);
        if (enemies.size() != 0) {
            int randIndex = rnd.nextInt(enemies.size());
            return enemies.get(randIndex);
        }
        return null;

    }

    public void accept(Unit u) {
        u.visit(this);
    }

    public void visit(Enemy e) {
        super.fight(e);
        if (!e.isAlive()) {
            onKill(e);
            switchCoordinate(e);
        }
    }

    protected void abilityDamage(Enemy e, int abilityDamage) {
        int defendPoint = e.defend();
        int damageDone = Math.max(abilityDamage - defendPoint, 0);
        messageCallBack.send(String.format("%s hit %s for %d ability damage.\n%s defend with %d defence points", getName(), e.getName(), abilityDamage,e.getName(),defendPoint));
        if (damageDone != 0 ){
            e.reduceHealth(damageDone);
            messageCallBack.send(String.format("%s Lost %d life Points, remain with %d/%d",e.getName(),damageDone,e.getHealthAmount(),e.getHealthPool()));
        }

        if (!e.isAlive())
            onKill(e);
    }

    protected void onKill(Enemy e) {
        int experienceGained = e.getExpValue();
        messageCallBack.send(String.format("%s died. %s gained %d experience", e.getName(), getName(), experienceGained));
        addExperience(experienceGained);

    }

    @Override
    public void onDeath() {
        messageCallBack.send("You lost.");
        deathCallBack.call();
    }

    protected void addExperience(int experienceGained) {
        this.experience += experienceGained;
        int nextLevelReq = levelUpRequirement();
        while (experience >= nextLevelReq) {
            levelUp();
            experience -= nextLevelReq;
            nextLevelReq = levelUpRequirement();
        }
    }

    protected void levelUp() {
        level++;
        int healthGained = gainHealth();
        int attackGained = gainAttack();
        int defenseGained = gainDefense();
        health.addCapacity(healthGained);
        health.fillToMax();
        attack += attackGained;
        defence += defenseGained;

        messageCallBack.send(String.format("%s reached level %d: +%d Health, +%d Attack, +%d Defense", getName(), getLevel(), healthGained, attackGained, defenseGained));

    }

    @Override
    public String toString() {
        return isAlive() ? super.toString() : "X";
    }


    public InputProvider getInputProvider() {
        return inputProvider;
    }

    protected int gainHealth() {
        return level * HEALTH_BONUS;
    }

    protected int gainAttack() {
        return level * ATTACK_BONUS;
    }

    protected int gainDefense() {
        return level * DEFENSE_BONUS;
    }

    private int levelUpRequirement() {
        return REQ_EXP * level;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public void visit(Player p) {
    }


    public String describe() {
        return String.format("%s\t\tLevel: %d\t\tHealth: %d/%d\t\tAttack Power: %d\t\tDefence Power: %d\t\tExperience: %d/%d", getName(), getLevel(), health.getAmount(), health.getCapacity(), attack, defence, getExperience(), levelUpRequirement());
    }

    public void play() {
        char action = inputProvider.getInput();
        if (action != 'e') {
            move(action);
        } else {
            if (abilityUsable()) {
                castAbility();
            } else {
                messageCallBack.send("you cant cast your ability now");
            }
        }
        endOfPlay();
    }

    protected List<Enemy> getEnemiesInRange(int range) {
        return enemiesInRangeCallBack.enemiesInRange(this.coordinate, range);
    }

    public void initializeToBoard(Coordinate coordinate, EnemiesInRangeCallBack enemiesInRangeCallBack, TileInCoordinateProvider tileInCoordinateProvider, MessageCallBack messageCallBack) {
        setCoordinate(coordinate);
        setEnemiesInRangeCallBack(enemiesInRangeCallBack);
        setTileInCoordinateProvider(tileInCoordinateProvider);
        setMessageCallBack(messageCallBack);
    }

    public void setEnemiesInRangeCallBack(EnemiesInRangeCallBack enemiesInRangeCallBack) {
        this.enemiesInRangeCallBack = enemiesInRangeCallBack;
    }

    protected abstract void endOfPlay();

    protected abstract boolean abilityUsable();

    protected abstract void castAbility();
}