public class Potion {
    private int count = 0;
    private int healamount = 0;

    //constructor
    public Potion(int count,int healamount){
        this.count = count;
        this.healamount = healamount;
        

    }

    //getters and setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getHealamount() {
        return healamount;
    }

    public void setHealamount(int healamount) {
        this.healamount = healamount;
    }

    
    public void showpotioninfo(){
        System.out.println("Cantidad: " + this.count);
        System.out.println("Vida: " + this.healamount);
    }
    
}
