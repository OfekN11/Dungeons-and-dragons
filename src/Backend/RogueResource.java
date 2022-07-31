package Backend;

public class RogueResource {
    private int cost;
    private int currentEnergy;

    public RogueResource(int cost) {
        this.cost = cost;
        this.currentEnergy = 100;
    }

    public int getCost() {
        return cost;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public void tick() {
        currentEnergy = Math.min(currentEnergy + 10, 100);
    }

    public void levelUpUPDATE() {
        currentEnergy = 100;
    }

    public boolean CastAbility() {
        if (currentEnergy >= cost) {
            currentEnergy -= cost;
            return true;
        }
        return false;
    }
}
