package Hospital_Database;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scannerObject = new Scanner (System.in);
        int option;

        do {
            System.out.println("Selecione o menu que deseja:");
            System.out.println("1 - Menu Médico");
            System.out.println("2 - Menu Enfermeiro");
            System.out.println("3 - Menu Administrador\n");

            option = scannerObject.nextInt();
            switch(option) {
                
                case 1 :
                    MedicMenu.medicMenuUserInterface();
                    break;
                case 2:
                    NurseMenu.nurseMenuUserInterface();
                    break;
                case 3:
                    AdministratorMenu.administratorMenuUserInterface();
                    break;
            
                default :
                    System.out.println("A opção inserida é inválida!");
                    scannerObject.close();
                    break;
            }
        }  while(option >= 1 && option <= 3 );
    
    }

}
