import java.util.Scanner;

public class MainGame {
    public static void main (String[] args) {
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
        }

        do {
            System.out.println("Escribe tu nombre: ");
            String name = sc.next();
            System.out.println(name);
        }while(game == true);
        
    }
}
