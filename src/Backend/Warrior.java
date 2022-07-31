package Backend;

import java.util.List;

public class Warrior extends Player {

    public final static int RANGE_ABILITY = 3;
    public WarriorResource warriorResource;


    public Warrior(String name, int health, int attack, int defence, int coolDown) {
        super(name,health,attack,defence);
        warriorResource = new WarriorResource(coolDown);
    }
    public void tick(){
       warriorResource.tick();
    }
    public void processSteps(){}
    public void levelUp(){
        super.levelUp();
        warriorResource.levelUpUPDATE();
        setHealthPool(getHealthPool() + 5 * getLevel());
        setAttack(getAttack() + 2 * getLevel());
        setDefence(gainDefense() + getLevel());
        health.fillToMax();
    }
    @Override
    protected void endOfPlay() {
       tick();
    }
    @Override
    protected boolean abilityUsable() {
        return warriorResource.getRemainingCooldown()==0;    }
    @Override
    protected void castAbility() {
        if (warriorResource.getRemainingCooldown() == 0) {
            warriorResource.setRemainingCooldown(warriorResource.getAbilityCooldown());
            setHealthAmount(Math.min(getHealthAmount() + 10 * defence, getHealthPool()));
            Enemy enemy = pickOneEnemy(RANGE_ABILITY);
            if (enemy != null) {
                abilityDamage(enemy, (int) (0.1 * getHealthPool()));
            }
        }
        else
            messageCallBack.send(String.format("%s cooldown didn't reached zero yet",getName()));
    }
}