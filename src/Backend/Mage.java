package Backend;

import java.util.List;

public class Mage extends Player{
    Resource mana;
    int abilityManaCost;
    int spellPower;
    int hitCount;
    int abilityRange;
    public Mage(String name, int health, int attack, int defence, int manaPool, int abilityManaCost, int spellPower, int hitCount, int abilityRange) {
        super(name,health,attack,defence);
        mana = new Resource(manaPool,manaPool/4);
        this.abilityManaCost = abilityManaCost;
        this.spellPower = spellPower;
        this.hitCount = hitCount;
        this.abilityRange = abilityRange;
    }

    @Override
    protected void endOfPlay() {
        mana.fill(level);
    }

    public void levelUp(){
        super.levelUp();
        mana.setCapacity(mana.getCapacity()+25*level);
        mana.fill( mana.getCapacity()/4);
        spellPower =spellPower + 10* level;
        health.fillToMax();
    }

    @Override
    protected boolean abilityUsable() {
        return mana.getAmount()>= abilityManaCost;
    }

    @Override
    protected void castAbility() {
        int hit = 0;
        List<Enemy> enemies = enemiesInRangeCallBack.enemiesInRange(this.coordinate,abilityRange);
        while (hit<hitCount & !enemies.isEmpty()){
            Enemy enemy = enemies.get(rnd.nextInt(enemies.size()));
            abilityDamage(enemy,spellPower);
            hit = hit +1;
            if (!enemy.isAlive())
                enemies.remove(enemy);
        }
        mana.reduce(abilityManaCost);
    }
    public String describe(){
        String output = super.describe();
        return String.format("%s mana: %d/%d",output,mana.getAmount(),mana.getCapacity());
    }
}
