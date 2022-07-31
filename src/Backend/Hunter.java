package Backend;

public class Hunter extends Player {

    private int range;
    private HunterResource hunterResource;
    public Hunter(String name, int health, int attack, int defence, int range) {
        super(name,health,attack,defence);
        this.range = range;
        hunterResource = new HunterResource(level*10);
    }
    public int getRange() {
        return range;
    }
    public void setRange(int range) {
        this.range = range;
    }
    public void levelUp(){
        super.levelUp();
        hunterResource.levelUpUPDATE(this);
        health.fillToMax();
    }

    @Override
    protected void endOfPlay() {
        tick();
    }

    @Override
    protected boolean abilityUsable() {
        return hunterResource.getArrowsCount()>0  ;
    }

    @Override
    protected void castAbility() {
        hunterResource.castAbility(this);

    }

    public void tick(){hunterResource.tick(this.getLevel());}

}
