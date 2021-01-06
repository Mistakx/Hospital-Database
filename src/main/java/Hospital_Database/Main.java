package Hospital_Database;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String hospitalDatabaseFilePath = "HospitalDB.txt";
        Hospital.getInstance(hospitalDatabaseFilePath);

        // //! DEBUG
        // System.out.println("DEBUG 1");
        // List<String> testStrings = new ArrayList<>();
        // System.out.println("DEBUG 2");
        // testStrings.add("TEST");
        // System.out.println("DEBUG 3");
        // System.out.println(testStrings.get(0));
        // //! DEBUG END



        Scanner scannerObject = new Scanner (System.in);

        //! DEBUG 2
        // String test = scannerObject.nextLine();
        // System.out.println(test);
        // System.out.println("debug: ");
        // test = scannerObject.nextLine();
        // System.out.println(test);
        // System.out.println("END DEBUG");
        //! DEBUG 2 END

        while (true) {
            
            ClearConsole.clearConsole();
            System.out.println("Selecione o menu que deseja:");
            System.out.println("1 - Menu Médico");
            System.out.println("2 - Menu Enfermeiro");
            System.out.println("3 - Menu Administrador\n");

            int option;
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
        }
    
    }

}
