import java.util.Scanner;


public class maingame {
    public static void main (String[] args) throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        boolean mainmenu = true;
        while(mainmenu == true){
        System.out.println("EL DESPERTAR DEL HEROE");
        System.out.println("----------------------");

        System.out.println("1. Nueva Partida");
        System.out.println("2. Consultar ranking");
        System.out.println("3. Ayuda");
        System.out.println("4. Salir");
        System.out.println("----------------------");
        System.out.println("Que Deseas Hacer?: ");
        int action = sc.nextInt();

        
        

        switch(action){
        case 1:
            mainmenu = true;
            mainmenu = false;
            break;
        case 2:
            //fetch xml with data from past games and if there are no past games say 'theres no past games'
            break;
        case 3:
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.println("En este juego vas a ser un heroe que busca salvar su aldea de unos malvados monstruos: \r\n" + //
                                "Monstruo 1: El Goblin\r\n" + //
                                "    Este sujeto tiene 60 de vida y quita 10 de vida por cada golpe que da\r\n" + //
                                "Monstruo 2: El Esqueleto\r\n" + //
                                "    Este sujeto tiene 90 de vida y quita 20 de vida por cada golpe que da\r\n" + //
                                "Monstruo 3: El Leviatán\r\n" + //
                                "    Este sujeto tiene 150 de vida y quita 15 de vida por cada golpe\r\n" + //
                                "\r\n" + //
                                "Tu heroe tiene 2 pociones normales que curan 20 de vida cada una y 1 super pocion que cura 50.\r\n" + //
                                "Puedes elegir entre 2 armas, espada larga, que quita 30 de vida y espada corta que quita 15 de vida\r\n" + //
                                "\r\n" + //
                                "Buena Suerte.\r\n" + //
                                "\r\n" + //
                                "");
            System.out.println("------------------------------------------------------------------------------------------------");
            Thread.sleep(5000);
            break;
        case 4:
            int[] leavegame = new int[Integer.MAX_VALUE -2];
            break;
            //idk how to leave this part so to leave the game ill crash this by creating a super long array
        default:
            System.out.println("No valido");
            System.out.println("---------");
            break;
        }
    }
        boolean game = true;

        System.out.println("----------------------");
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
                System.out.println("----------------------");
                System.out.println("Te encuentras descansando ahora mismo");
                System.out.println("-------------------------------------");
                Thread.sleep(1000);
                System.out.println("1. Comenzar " + "2. Seguir descansando"  + "3. Salir");
                int walkquestion = sc.nextInt();

                switch(walkquestion){
                case 1:
                    resting = false;
                    continue;

                case 2:
                    Thread.sleep(5000);
                    break;

                case 3:
                    int[] leavegame = new int[Integer.MAX_VALUE -2];

                default:
                    continue;
                }

            }

            if(goblin.getHealth() > 0){
                boolean goblinfight = true;
                System.out.println("---------------------------------------------------------------");
                System.out.println("Estas andando por una cueva en busca de quien destruyo tu aldea");
                Thread.sleep(2000);
                System.out.println("Escuchas unas piedras moverse detras de ti...");
                Thread.sleep(1000);
                System.out.println("Aparece tu primer enemigo, El Goblin.");
                Thread.sleep(2000);

                while(goblinfight == true){

                    boolean battlemenu = true;

                    while(battlemenu == true){
                        System.out.println("----------------------");
                        System.out.println("Que quieres hacer ahora?");

                        System.out.println("1. Atacar");
                        System.out.println("2. Curarse");
                        System.out.println("3. Ver Stats");

                        int battledecision = sc.nextInt();
                        switch (battledecision){
                        case 1:
                            System.out.println("----------------------");
                            System.out.println("Con que espada?");
                            System.out.println("------------------------------------");
                            System.out.println("1. Espada Larga" + " 2. Espada Corta");
                            int espadadecision = sc.nextInt();
                            if(espadadecision == 1){
                                hero.setDamage(longsword.getDamageamount());
                                goblin.setHealth(goblin.getHealth() - hero.getDamage());
                                System.out.println("----------------------");
                                System.out.println("Le has hecho 30 de daño");

                            }
                            else if(espadadecision == 2){
                                hero.setDamage(shortsword.getDamageamount());
                                System.out.println("----------------------");
                                goblin.setHealth(goblin.getHealth() - hero.getDamage());
                                System.out.println("Le has hecho 15 de daño");

                            }
                            System.out.println("-----------------------------------------");
                            System.out.println("El Goblin te golpea y te quita 10 de vida");
                            hero.setHealth(hero.getHealth() - goblin.getDamage());
                            System.out.println("--------------------------------------------------");
                            System.out.println("Te quedan " + hero.getHealth() + " Puntos de vida.");
                            break;
                        case 2:
                            System.out.println("------------------------");
                            System.out.println("Que pocion quieres usar?");
                            System.out.println("-------------------------------------");
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
                            System.out.println("-----------------");
                            System.out.println("DATOS DEL HEROE: "); hero.showcharacterdata();
                            System.out.println("Super Pociones: " + superPotion.getCount()); 
                            System.out.println("Pociones Normales: " + normalPotion.getCount());
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
                            System.out.println("-----------------");
                            System.out.println("DATOS DEL HEROE: "); hero.showcharacterdata();
                            System.out.println("Super Pociones: " + superPotion.getCount()); 
                            System.out.println("Pociones Normales: " + normalPotion.getCount());
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

            if(leviathan.getHealth() > 0){
                boolean leviathanfight = true;

                System.out.println("Despues de vencer al goblin y al rey esqueleto decides seguir el camino.");
                Thread.sleep(2000);
                System.out.println("Ahora mismo te encuentras surcando los mares en busca de tu ultimo enemigo");
                Thread.sleep(1000);
                System.out.println("Aparece tu ultimo enemigo, El Leviatan");
                Thread.sleep(2000);

                while(leviathanfight == true){

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
                                leviathan.setHealth(leviathan.getHealth() - hero.getDamage());
                                System.out.println("Le has hecho 30 de daño");

                            }
                            else if(espadadecision == 2){
                                hero.setDamage(shortsword.getDamageamount());
                                leviathan.setHealth(leviathan.getHealth() - hero.getDamage());
                                System.out.println("Le has hecho 15 de daño");

                            }

                            System.out.println("El Leviatan te golpea y te quita 15 de vida");
                            hero.setHealth(hero.getHealth() - leviathan.getDamage());
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
                            System.out.println("-----------------");
                            System.out.println("DATOS DEL HEROE: "); hero.showcharacterdata();
                            System.out.println("Super Pociones: " + superPotion.getCount()); 
                            System.out.println("Pociones Normales: " + normalPotion.getCount());
                            System.out.println("-----------------");
                            System.out.println("DATOS DEL ENEMIGO: "); leviathan.showcharacterdata();
                            continue;
                        }
                        
                        if(leviathan.getHealth() <= 0){
                        leviathanfight = false;
                        battlemenu = false;
                        }
                    }

                }
            }

            if(goblin.getHealth() <= 0 && skeleton.getHealth() <= 0 && leviathan.getHealth() <= 0){
                System.out.println("HAS VENCIDO A TODOS TUS ENEMIGOS");
                System.out.println("Gracias por jugar :D");
                //rollout credits
                
            }

        
        } while(game == true);

    }
}
