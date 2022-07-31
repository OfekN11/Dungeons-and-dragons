package Backend;

import java.util.Iterator;
import java.util.List;

public class Rogue extends Player{

    public RogueResource rogueResource;

    public Rogue(String Name, int healthCapacity, int attack, int defence, int energyCost) {
        super(Name, healthCapacity, attack, defence);
        rogueResource = new RogueResource(energyCost);
    }

    public void levelUp(){
        super.levelUp();
        rogueResource.levelUpUPDATE();
        health.fillToMax();
        setAttack(getAttack() + 3 * level);
    }

    @Override
    protected void endOfPlay() {
        tick();
    }

    @Override
    protected boolean abilityUsable() {
        return rogueResource.getCurrentEnergy()>=rogueResource.getCost();
    }

    @Override
    protected void castAbility() {
        if(rogueResource.CastAbility())
        {
            List<Enemy> enemies = EnemiesInRangeCallBack(getCoordinate(),2);
            for(Enemy e: enemies)
                super.abilityDamage(e,attack);
        }
        else
            messageCallBack.send(String.format("%s doesn't have enough energy",getName()));
    }

    public void tick(){
        rogueResource.tick();
    }

    
}
