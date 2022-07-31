package Backend;

public class Trap extends Enemy {

    private int visibilityTime;
    private int invisibilityTime;
    int tickCounter;
    private boolean isVisible;
    public Trap(char tile, String name, int health, int attack, int defence, int expValue, int visibilityTime, int invisibilityTime) {
        super(tile,name,health,attack,defence,expValue,2);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        tickCounter = 0;
        isVisible = true;
    }


    public String toString(){
        return isVisible ? super.toString() : ".";
    }

    @Override
    public void play() {
        Player player = playerInRangeCallBack.check(this.coordinate,visionRange);
        if (player != null) {
            fight(player);
        }
        tickCounter ++;
        updateVisibility();
    }

    private void updateVisibility() {
        if (isVisible & visibilityTime == tickCounter){
            tickCounter = 0;
            isVisible =false;
        }

        if (!isVisible & invisibilityTime == tickCounter){
            tickCounter = 0;
            isVisible =true;
        }
    }
}
