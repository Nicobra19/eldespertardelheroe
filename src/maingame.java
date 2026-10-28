import java.util.Scanner;

public class maingame {
    public static void main (String[] args){
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

        switch(action){
            case 1: 
                boolean game = true;
                break;
            case 2: 
                //fetch xml with data from past games and if there are no past games say 'theres no past games'
                break;
            case 3:
                
        }

    }
}
