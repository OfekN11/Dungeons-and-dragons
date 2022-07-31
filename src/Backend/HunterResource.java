package Backend;

public class HunterResource {
    private int arrowsCount;
    private int ticksCount;
    public HunterResource(int arrowsCount){
        this.arrowsCount = arrowsCount;
        this.ticksCount = 0;

    }

    public int getArrowsCount() {
        return arrowsCount;
    }

    public int getTicksCount() {
        return ticksCount;
    }

    public void setArrowsCount(int arrowsCount) {
        this.arrowsCount = arrowsCount;
    }

    public void setTicksCount(int ticksCount) {
        this.ticksCount = ticksCount;
    }

    public void levelUpUPDATE(Hunter hunter) {
        arrowsCount = arrowsCount + 10 *hunter.getLevel();
        hunter.setAttack(hunter.getAttack()+ 2 *hunter.getLevel());
        hunter.setDefence(hunter.getDefence()+ hunter.getLevel());
    }
    public void tick(int level) {
        if (ticksCount==10)
        {
            arrowsCount = arrowsCount+level;
            ticksCount=0;
        }
        else ticksCount++;
    }

    public void castAbility(Hunter hunter) {
        arrowsCount = arrowsCount-1;
        //continue


    }


}
