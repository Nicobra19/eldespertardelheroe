import java.util.Scanner;


public class maingame {
    public static void main (String[] args) throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        
        while(true){
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
        int action = readInt(sc);

        
        

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
                                "    Este sujeto tiene 40 de vida y quita 10 de vida por cada golpe que da\r\n" + //
                                "Monstruo 2: El Esqueleto\r\n" + //
                                "    Este sujeto tiene 60 de vida y quita 20 de vida por cada golpe que da\r\n" + //
                                "Monstruo 3: El Leviatán\r\n" + //
                                "    Este sujeto tiene 80 de vida y quita 15 de vida por cada golpe\r\n" + //
                                "\r\n" + //
                                "Tu heroe tiene 2 pociones normales que curan 25 de vida cada una y 1 super pocion que cura 50.\r\n" + //
                                "Puedes elegir entre 2 armas, espada larga, que quita de 15 a 25 de vida y espada corta que quita 10 a 15 de vida\r\n" + //
                                "\r\n" + //
                                "Buena Suerte.\r\n" + //
                                "\r\n" + //
                                "");
            System.out.println("------------------------------------------------------------------------------------------------");
            Thread.sleep(5000);
            break;
        case 4:
            return;
        default:
            System.out.println("No valido");
            System.out.println("---------");
            break;
        }
    }
        boolean game = true;
        int shortdamage = (int) (Math.random() * 6) + 10;
        int longdamage = (int) (Math.random() * 11) + 15;
        int puntos = 0;

        System.out.println("----------------------");
        System.out.println("Escribe tu nombre: ");
        String name = sc.next();

        Hero hero = new Hero(name, 100, 5);
        LongSword longsword = new LongSword(longdamage);
        ShortSword shortsword = new ShortSword(shortdamage);
        Goblin goblin = new Goblin("El Goblin", 40, 10);
        SuperPotion superPotion = new SuperPotion( 1,50);
        NormalPotion normalPotion = new NormalPotion(2, 25);
        Skeleton skeleton = new Skeleton("El Rey Esqueleto", 60, 25);
        Leviathan leviathan = new Leviathan("El Leviatán", 80, 18);
        do {

            boolean resting = true;
            while(resting == true){
                System.out.println("-------------------------------------");
                System.out.println("Te encuentras descansando ahora mismo");
                System.out.println("-------------------------------------");
                Thread.sleep(1000);
                System.out.println("1. Comenzar " + "2. Seguir descansando"  + " 3. Salir");
                int walkquestion = readInt(sc);

                switch(walkquestion){
                case 1:
                    resting = false;
                    continue;

                case 2:
                    Thread.sleep(5000);
                    break;

                case 3:
                    return;

                default:
                    System.out.println("---------");
                    System.out.println("No valido");
                    System.out.println("---------");
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

                        int battledecision = readInt(sc);
                        switch (battledecision){
                        case 1:
                            System.out.println("----------------------");
                            System.out.println("Con que espada?");
                            System.out.println("------------------------------------");
                            System.out.println("1. Espada Larga" + " 2. Espada Corta");
                            int espadadecision = readInt(sc);
                            if(espadadecision == 1){
                                hero.setDamage(longsword.getDamageamount());
                                goblin.setHealth(goblin.getHealth() - hero.getDamage());
                                System.out.println("----------------------");
                                System.out.println("Le has hecho " + longdamage + " de daño");

                            }  
                            
                            else if(espadadecision == 2){
                                hero.setDamage(shortsword.getDamageamount());
                                System.out.println("----------------------");
                                goblin.setHealth(goblin.getHealth() - hero.getDamage());
                                System.out.println("Le has hecho " + shortdamage + " de daño");

                            }else{
                                System.out.println("---------");
                                System.out.println("No valido");
                                System.out.println("---------");
                                continue;
                            }

                            if(goblin.getHealth() <= 0){
                                puntos = puntos + 100;
                                goblinfight = false;
                                battlemenu = false;
                                
                            }else{
                                System.out.println("-----------------------------------------");
                                System.out.println("El Goblin te golpea y te quita 10 de vida");
                                hero.setHealth(hero.getHealth() - goblin.getDamage());
                            }
                            
                            

                            
                            if(hero.getHealth() <= 0){
                                System.out.println("Has Muerto");
                                System.out.println("Saliendo del juego en 5s");
                                Thread.sleep(5000);
                                return;
                            }else{
                            System.out.println("--------------------------------------------------");
                            System.out.println("Te quedan " + hero.getHealth() + " Puntos de vida.");}
                            break;
                        case 2: //porque el enemigo te pegaria si te estas echando una pocion, no me gusta, al igual que si quieres meterte una pocion aunque sepas que no te va a curar, porque no puedes hacerlo?
                            System.out.println("------------------------");
                            System.out.println("Que pocion quieres usar?");
                            System.out.println("-------------------------------------");
                            System.out.println("1. Super pocion" + " 2. Pocion Normal");
                            int pociondecision = readInt(sc);

                            if (pociondecision == 1){
                                if(superPotion.getCount() > 0){
                                    superPotion.setCount(superPotion.getCount() - 1);
                                    hero.setHealth(hero.getHealth() + superPotion.getHealamount());

                                if (hero.getHealth() > 100){
                                    hero.setHealth(100);
                                }
                                }else{
                                    System.out.println("---------------------------");
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
                                    System.out.println("------------------------------");
                                    System.out.println("No te quedan Pociones Normales");
                                }
                            }else {
                                System.out.println("---------");
                                System.out.println("No valido");
                                System.out.println("---------");
                                continue;
                            }
                            
                            break;
                        case 3:
                            System.out.println("-----------------");
                            System.out.println("DATOS DEL HEROE: "); hero.showcharacterdata();
                            System.out.println("Super Pociones: " + superPotion.getCount()); 
                            System.out.println("Pociones Normales: " + normalPotion.getCount());
                            System.out.println("Puntos: " + puntos);
                            System.out.println("-----------------");
                            System.out.println("DATOS DEL ENEMIGO: "); goblin.showcharacterdata();
                            break;
                        
                            default:
                                System.out.println("---------");
                                System.out.println("No Valido");
                                System.out.println("---------");
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
                        System.out.println("----------------------");
                        System.out.println("Que quieres hacer ahora?");

                        System.out.println("1. Atacar");
                        System.out.println("2. Curarse");
                        System.out.println("3. Ver Stats");

                        int battledecision = readInt(sc);
                        switch (battledecision){
                        case 1:
                            System.out.println("---------------");
                            System.out.println("Con que espada?");
                            System.out.println("1. Espada Larga" + " 2. Espada Corta");
                            int espadadecision = readInt(sc);
                            if(espadadecision == 1){
                                hero.setDamage(longsword.getDamageamount());
                                skeleton.setHealth(skeleton.getHealth() - hero.getDamage());
                                System.out.println("Le has hecho " + longdamage + " de daño");

                            }
                            else if(espadadecision == 2){
                                hero.setDamage(shortsword.getDamageamount());
                                skeleton.setHealth(skeleton.getHealth() - hero.getDamage());
                                System.out.println("Le has hecho " + shortdamage + " de daño");

                            }else{
                                System.out.println("---------");
                                System.out.println("No valido");
                                System.out.println("---------");
                                continue;
                            }
                            if(skeleton.getHealth() <= 0){
                                puntos = puntos + 100;
                                skeletonfight = false;
                                battlemenu = false;
                            }else{
                                System.out.println("------------------------------------------------");
                                System.out.println("El Rey Esqueleto te golpea y te quita 20 de vida");
                                hero.setHealth(hero.getHealth() - skeleton.getDamage());
                            }


                            if(hero.getHealth() <= 0){
                                System.out.println("Has Muerto");
                                System.out.println("Saliendo del juego en 5s");
                                Thread.sleep(5000);
                                return;
                            }else{
                            System.out.println("--------------------------------------------------");
                            System.out.println("Te quedan " + hero.getHealth() + " Puntos de vida.");}
                            break;

                        case 2:
                            System.out.println("------------------------");
                            System.out.println("Que pocion quieres usar?");
                            System.out.println("1. Super pocion" + " 2. Pocion Normal");
                            int pociondecision = readInt(sc);

                            if (pociondecision == 1){
                                if(superPotion.getCount() > 0){
                                    superPotion.setCount(superPotion.getCount() - 1);
                                    hero.setHealth(hero.getHealth() + superPotion.getHealamount());

                                if (hero.getHealth() > 100){
                                    hero.setHealth(100);
                                }
                                }else{
                                    System.out.println("---------------------------");
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
                                    System.out.println("------------------------------");
                                    System.out.println("No te quedan Pociones Normales");
                                }
                            }else{
                                System.out.println("---------");
                                System.out.println("No valido");
                                System.out.println("---------");
                                continue;
                            }
                            break;
                        case 3:
                            System.out.println("-----------------");
                            System.out.println("DATOS DEL HEROE: "); hero.showcharacterdata();
                            System.out.println("Super Pociones: " + superPotion.getCount()); 
                            System.out.println("Pociones Normales: " + normalPotion.getCount());
                            System.out.println("Puntos: " + puntos);
                            System.out.println("-----------------");
                            System.out.println("DATOS DEL ENEMIGO: "); skeleton.showcharacterdata();
                            continue;
                        default:
                            System.out.println("---------");
                            System.out.println("No valido");
                            System.out.println("---------");
                            continue;
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
                        System.out.println("------------------------");
                        System.out.println("Que quieres hacer ahora?");

                        System.out.println("1. Atacar");
                        System.out.println("2. Curarse");
                        System.out.println("3. Ver Stats");

                        int battledecision = readInt(sc);
                        switch (battledecision){
                        case 1:
                            System.out.println("---------------");
                            System.out.println("Con que espada?");
                            System.out.println("1. Espada Larga" + " 2. Espada Corta");
                            int espadadecision = readInt(sc);
                            if(espadadecision == 1){
                                hero.setDamage(longsword.getDamageamount());
                                leviathan.setHealth(leviathan.getHealth() - hero.getDamage());
                                System.out.println("Le has hecho " + longdamage + " de daño");

                            }
                            else if(espadadecision == 2){
                                hero.setDamage(shortsword.getDamageamount());
                                leviathan.setHealth(leviathan.getHealth() - hero.getDamage());
                                System.out.println("Le has hecho " + shortdamage + " de daño");

                            }else{
                                System.out.println("---------");
                                System.out.println("No valido");
                                System.out.println("---------");
                                continue;
                            }

                            if(leviathan.getHealth() <= 0){
                                puntos = puntos + 100;
                                leviathanfight = false;
                                battlemenu = false;
                            }else{
                            System.out.println("-------------------------------------------");
                            System.out.println("El Leviatan te golpea y te quita 15 de vida");
                            hero.setHealth(hero.getHealth() - leviathan.getDamage());
                            }


                            if(hero.getHealth() <= 0){
                                System.out.println("Has Muerto");
                                System.out.println("Saliendo del juego en 5s");
                                Thread.sleep(5000);
                                return;
                            }else{
                            System.out.println("--------------------------------------------------");
                            System.out.println("Te quedan " + hero.getHealth() + " Puntos de vida.");}
                            break;

                        case 2:
                            System.out.println("------------------------");
                            System.out.println("Que pocion quieres usar?");
                            System.out.println("1. Super pocion" + " 2. Pocion Normal");
                            int pociondecision = readInt(sc);

                            if (pociondecision == 1){
                                if(superPotion.getCount() > 0){
                                    superPotion.setCount(superPotion.getCount() - 1);
                                    hero.setHealth(hero.getHealth() + superPotion.getHealamount());

                                if (hero.getHealth() > 100){
                                    hero.setHealth(100);
                                }
                                }else{
                                    System.out.println("---------------------------");
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
                                    System.out.println("------------------------------");
                                    System.out.println("No te quedan Pociones Normales");
                                }
                            }else{
                                System.out.println("No valido");
                                System.out.println("---------");
                                continue;
                            }
                            break;
                        case 3:
                            System.out.println("-----------------");
                            System.out.println("DATOS DEL HEROE: "); hero.showcharacterdata();
                            System.out.println("Super Pociones: " + superPotion.getCount()); 
                            System.out.println("Pociones Normales: " + normalPotion.getCount());
                            System.out.println("Puntos: " + 600);
                            System.out.println("-----------------");
                            System.out.println("DATOS DEL ENEMIGO: "); leviathan.showcharacterdata();
                            continue;
                        default:
                            System.out.println("---------");
                            System.out.println("No valido");
                            System.out.println("---------");
                            continue;
                        }
                        

                    }

                }
            }

            if(goblin.getHealth() <= 0 && skeleton.getHealth() <= 0 && leviathan.getHealth() <= 0){
                puntos = puntos + hero.getHealth();
                System.out.println("HAS VENCIDO A TODOS TUS ENEMIGOS");
                System.out.println("Gracias por jugar :D");
                System.out.println("Puntuacion: " + puntos);
                //rollout credits
                
                System.out.println("1. Volver al menu");
                System.out.println("2. Salir");
                int menufinal = readInt(sc);
                switch(menufinal){
                    case 1:
                        game = false;
                        mainmenu = true;
                        break;
                    case 2:
                        return;
                }
            }

        
        } while(game == true);

        }
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            if (!sc.hasNext()) {
                System.out.println("No hay más entrada. Saliendo del juego.");
                System.exit(0);
            }
            System.out.println("Introduce un número válido.");
            sc.next();
        }
        return sc.nextInt();
    }
}
