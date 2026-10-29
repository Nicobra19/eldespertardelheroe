public class NormalPotion extends Potion{
    
    NormalPotion(int count, int healamount){
        this.setCount(2);
        this.setHealamount(20);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return super.getCount();
    }

    @Override
    public int getHealamount() {
        // TODO Auto-generated method stub
        return super.getHealamount();
    }

    @Override
    public void setCount(int count) {
        // TODO Auto-generated method stub
        super.setCount(count);
    }

    
}
