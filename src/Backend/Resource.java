package Backend;

public class Resource {
    int capacity;
    int amount;
    public Resource(int capacity){
        this.capacity = capacity;
        this.amount = capacity;
    }

    public Resource(int capacity, int startAmount){
        this.capacity = capacity;
        this.amount = startAmount;
    }


    public int getCapacity(){
        return capacity;
    }
    public int getAmount(){
        return amount;
    }
    public boolean isEmpty(){
        return amount==0;
    }
    public void setCapacity(int newPool){
        capacity = newPool;
    }
    public void setAmount(int newAmount){
        amount=newAmount;
    }
    public boolean enough(int utilization){
        return utilization<=amount;
    }
    public void reduce(int amount){
        this.amount = Math.max(this.amount - amount,0);
    }
    public void fillToMax(){amount = capacity;}

    public void fill(int toFill){
        amount = Math.min(amount+toFill,capacity);
    }

    public void addCapacity(int amount) {
        capacity = capacity + amount;
    }
}
