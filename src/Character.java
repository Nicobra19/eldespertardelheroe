public class Character {
    private String name = "Character";
    private int health = 0;
    private int damage = 0;

    //constructor
    public Character(){

    }
    
    
    public Character(String name, int health,int damage){
        this.name = name;
        this.health = health;
        this.damage = damage;
    }


    //getters and setters
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getHealth() {
        return health;
    }


    public void setHealth(int health) {
        this.health = health;
    }


    public int getDamage() {
        return damage;
    }


    public void setDamage(int damage) {
        this.damage = damage;
    }

    
    public void showcharacterdata(){
        System.out.println("Nombre: " + this.name);
        System.out.println("Vida: " + this.health);
        System.out.println("Daño: " + this.damage);
    }
    
}