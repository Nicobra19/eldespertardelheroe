import java.util.Scanner;

public class maingame {
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

        Hero hero = new Hero(name, 100, 5);
        LongSword longsword = new LongSword(30);
        ShortSword shortsword = new ShortSword(15);
        Goblin goblin = new Goblin("El Goblin", 60, 10);
        SuperPotion superPotion = new SuperPotion( 1,50);
        NormalPotion normalPotion = new NormalPotion(2, 20);
        Skeleton skeleton = new Skeleton("El Rey Esqueleto", 90, 20);
        Leviathan leviathan = new Leviathan("El Leviatán", 150, 15);
        do {

            boolean resting = true;
            while(resting == true){
                System.out.println("Te encuentras descansando ahora mismo");
                Thread.sleep(1000);
                System.out.println("Andar?");
                System.out.println("1. Si" + " 2. No");
                int walkquestion = sc.nextInt();

                switch(walkquestion){
                case 1:
                    resting = false;
                    continue;

                case 2:
                    break;
                }

            }

            if(goblin.getHealth() > 0){
                boolean goblinfight = true;

                System.out.println("Estas andando por una cueva en busca de quien destruyó tu aldea");
                Thread.sleep(2000);
                System.out.println("Escuchas unas piedras moverse detrás de ti...");
                Thread.sleep(1000);
                System.out.println("Aparece tu primer enemigo, El Goblin.");
                Thread.sleep(2000);

                while(goblinfight == true){

                    boolean battlemenu = true;

                    while(battlemenu == true){

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

                            System.out.println("El Goblin te golpea y te quita 10 de vida");
                            hero.setHealth(hero.getHealth() - goblin.getDamage());
                            System.out.println("Te quedan " + hero.getHealth() + " Puntos de vida.");
                            break;
                        case 2:
                            System.out.println("Que pocion quieres usar?");
                            System.out.println("1. Super pocion" + " 2. Pocion Normal");
                            int pociondecision = sc.nextInt();

                            if (pociondecision == 1){
                                if(superPotion.getCount() > 0){
                                    superPotion.setCount(superPotion.getCount() - 1);
                                    hero.setHealth(hero.getHealth() + superPotion.getHealamount());

                                if (hero.getHealth() > 100){
                                    hero.setHealth(100);
                                }
                                }else{
                                    System.out.println("No te quedan Super Pociones");
                                }
                                
  
                            }
                            else if(pociondecision == 2){
                                if(normalPotion.getCount() > 0){
                                    normalPotion.setCount(normalPotion.getCount() - 1);
                                    hero.setHealth(hero.getHealth() + normalPotion.getHealamount());

                                if (hero.getHealth() > 100){
                                    hero.setHealth(100);
                                }
                                }else{
                                    System.out.println("No te quedan Pociones Normales");
                                }
                            }
                            break;
                        case 3:

                            System.out.println("DATOS DEL HEROE: "); hero.showcharacterdata();
                            System.out.println("-----------------");
                            System.out.println("DATOS DEL ENEMIGO: "); goblin.showcharacterdata();
                            continue;

                        }

                        if(goblin.getHealth() <= 0){
                            goblinfight = false;
                            battlemenu = false;
                        }

                    }
                }
            }
            if(skeleton.getHealth() > 0){
                boolean skeletonfight = true;

                System.out.println("Despues de vencer al goblin, decides seguir el camino.");
                Thread.sleep(2000);
                System.out.println("Te encuentras con un cementerio");
                Thread.sleep(1000);
                System.out.println("Aparece tu segundo enemigo, El Rey Esqueleto");
                Thread.sleep(2000);

                while(skeletonfight == true){

                    boolean battlemenu = true;

                    while(battlemenu == true){

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
                                skeleton.setHealth(skeleton.getHealth() - hero.getDamage());
                                System.out.println("Le has hecho 30 de daño");

                            }
                            else if(espadadecision == 2){
                                hero.setDamage(shortsword.getDamageamount());
                                skeleton.setHealth(skeleton.getHealth() - hero.getDamage());
                                System.out.println("Le has hecho 15 de daño");

                            }

                            System.out.println("El Rey Esqueleto te golpea y te quita 20 de vida");
                            hero.setHealth(hero.getHealth() - skeleton.getDamage());
                            System.out.println("Te quedan " + hero.getHealth() + " Puntos de vida.");
                            break;

                        case 2:
                            System.out.println("Que pocion quieres usar?");
                            System.out.println("1. Super pocion" + " 2. Pocion Normal");
                            int pociondecision = sc.nextInt();

                            if (pociondecision == 1){
                                if(superPotion.getCount() > 0){
                                    superPotion.setCount(superPotion.getCount() - 1);
                                    hero.setHealth(hero.getHealth() + superPotion.getHealamount());

                                if (hero.getHealth() > 100){
                                    hero.setHealth(100);
                                }
                                }else{
                                    System.out.println("No te quedan Super Pociones");
                                }
                                
  
                            }
                            else if(pociondecision == 2){
                                if(normalPotion.getCount() > 0){
                                    normalPotion.setCount(normalPotion.getCount() - 1);
                                    hero.setHealth(hero.getHealth() + normalPotion.getHealamount());

                                if (hero.getHealth() > 100){
                                    hero.setHealth(100);
                                }
                                }else{
                                    System.out.println("No te quedan Pociones Normales");
                                }
                            }
                            break;
                        case 3:

                            System.out.println("DATOS DEL HEROE: "); hero.showcharacterdata();
                            System.out.println("-----------------");
                            System.out.println("DATOS DEL ENEMIGO: "); skeleton.showcharacterdata();
                            continue;
                        }
                        
                        if(skeleton.getHealth() <= 0){
                        skeletonfight = false;
                        battlemenu = false;
                        }
                    }
                }

            }

        
        } while(game == true);

    }
}
