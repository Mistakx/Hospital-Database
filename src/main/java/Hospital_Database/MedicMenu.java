/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital_Database;

import java.util.Scanner;

/**
 *
 * @author mistakx
 */
public class MedicMenu {

    static Scanner scannerObject = new Scanner(System.in); // Create a Scanner object

    public static void medicMenuUserInterface() {

        System.out.println("Escolha a opção que deseja.");
        System.out.println("1 - Listar pacientes em espera no hospital.");
        System.out.println("2 - Listar pacientes a aguardar alta.");
        System.out.println("3 - Diagnóstico ao paciente.");
        System.out.println("4 - Dar alta hospitalar.");
        System.out.println("5 - Requerimento de auxiliares.\n");

        int option;

        do {

            option = scannerObject.nextInt();

            switch (option) {

                case 1:
                    listPacientsInHospitalQueue();
                    // Show all Pacients waiting on the hospital
                    break;
                case 2:
                    listPacientsAwaitingDischarge();;
                    // Show all pacients waiting to receive good news from the doctor
                    break;
                case 3:
                    pacientDiagnostic();
                    // Show the diagnostic of a certain person, needs to choose the right ID
                    break;
                case 4:
                    dischargePacient();
                    // Give good news, Need To see all the details on the enunciado
                    break;
                case 5:
                    requisitAuxiliaryNurses();
                    // Doctor needs help, send some auxiliary nurses immediatly
                    break;
                default:
                    System.out.println("Opção inválida\n");
                    scannerObject.close();

            }
        } while (option >= 1 && option <= 5);

    }

    public static void listPacientsInHospitalQueue() {
        // TODO
    }

    private static void listPacientsAwaitingDischarge() {
        // TODO
    }

    private static void pacientDiagnostic() {
        // TODO
    }

    private static void dischargePacient() {
        // TODO
    }

    private static void requisitAuxiliaryNurses() {
        // TODO
    }

}
