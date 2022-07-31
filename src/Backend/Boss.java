package Backend;

public class Boss extends Monster {
    private int abilityFrequency;
    private int combatTicks;
    public Boss(char tile,String name,int health,int attack, int defence,int expValue,int visionRange,int abilityFrequency) {
        super(tile,name,health,attack,defence,expValue,visionRange);
        this.abilityFrequency = abilityFrequency;
        this.combatTicks = 0;

    }

    public int getAbilityFrequency() {
        return abilityFrequency;
    }

    public int getCombatTicks() {
        return combatTicks;
    }

}
