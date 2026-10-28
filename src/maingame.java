import java.util.Scanner;

public class MainGame {
    public static void main (String[] args) throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        System.out.println("EL DESPERTAR DEL HEROE");
        System.out.println("----------------------");
        
       

        System.out.println("1. Nueva Partida");
        System.out.println("2. Consultar ranking");
        System.out.println("3. Ayuda");
        System.out.println("4. Salir");
        System.out.println("----------------------");
        System.out.println("Que Deseas Hacer?: ");
        int action = sc.nextInt();

        boolean game = false;
        switch(action){
            case 1: 
                game = true;
                break;
            case 2: 
                //fetch xml with data from past games and if there are no past games say 'theres no past games'
                break;
            case 3:
                //show info
                break;
            case 4:
                break;
            default: 
                System.out.println("No valido");
                break;
        }
        
        System.out.println("Escribe tu nombre: ");
            String name = sc.next();
            
            Hero hero = new Hero(name, 100, 0);
            LongSword longsword = new LongSword(30);
            ShortSword shortsword = new ShortSword(15);
            Goblin goblin = new Goblin("El Goblin", 40, 10);
            SuperPotion superPotion = new SuperPotion( 1,50);
            NormalPotion normalPotion = new NormalPotion(2, 20);
            
        do {
            System.out.println("Estas andando por una cueva en busca de quien destruyó tu aldea");
            Thread.sleep(2000);
            System.out.println("Escuchas unas piedras moverse detrás de ti...");
            Thread.sleep(1000);
            System.out.println("Aparece tu primer enemigo, El Goblin.");
            Thread.sleep(2000);

            System.out.println("Que quieres hacer ahora?");

            System.out.println("1. Atacar");
            System.out.println("2. Curarse");
            System.out.println("3. Ver Stats");
            
            int battledecision = sc.nextInt();
            switch (battledecision){
                case 1:
                    System.out.println("Con que espada?");
                    System.out.println("1. Espada Larga" + " 2. Espada Corta");
                    int espadadecision = sc.nextInt();
                    if(espadadecision == 1){
                        hero.setDamage(longsword.getDamageamount());
                        goblin.setHealth(goblin.getHealth() - hero.getDamage());
                        System.out.println("Le has hecho 30 de daño");
                        
                    }
                    else if(espadadecision == 2){
                        hero.setDamage(shortsword.getDamageamount());
                        goblin.setHealth(goblin.getHealth() - hero.getDamage());
                        System.out.println("Le has hecho 15 de daño");
                        
                    }
                    break;
                case 2: 
                    System.out.println("Que pocion quieres usar?");
                    System.out.println("1. Super pocion" + " 2. Pocion Normal");
                    int pociondecision = sc.nextInt();

                    if (pociondecision == 1){
                        hero.setHealth(hero.getHealth() + superPotion.getHealamount());
                        if (hero.getHealth() < 100){
                            hero.setHealth(100);
                        }
                    }
                    else if(pociondecision == 2){
                        hero.setHealth(hero.getHealth() + normalPotion.getHealamount());
                        if (hero.getHealth() < 100){
                            hero.setHealth(100);
                        }
                    }
                    break;
                case 3:
                    
                System.out.println("DATOS DEL HEROE: "); hero.showcharacterdata();



                    
            }
            
            
            System.out.println("El Goblin te golpea y te quita 10 de vida");
            hero.setHealth(hero.getHealth() - goblin.getDamage());
            System.out.println("Te quedan " + hero.getHealth() + " Puntos de vida.");
        }while(game == true);
        
    }
}
