package Backend;

public class WarriorResource {

    private int abilityCooldown;
    private int remainingCooldown;

    public WarriorResource(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
        this.remainingCooldown = 0;
    }
    public int getAbilityCooldown() {
        return this.abilityCooldown;
    }
    public int getRemainingCooldown() {
        return this.remainingCooldown;
    }
    public void setAbilityCooldown(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
    }

    public void setRemainingCooldown(int remainingCooldown) {
        this.remainingCooldown = remainingCooldown;
    }
    public void tick() {
        if (remainingCooldown > 0)
            remainingCooldown--;
    }

    public void levelUpUPDATE() {
        remainingCooldown = 0;
    }
}
