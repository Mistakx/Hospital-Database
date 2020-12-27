/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital_Database;

import java.util.Queue;
import java.util.Scanner;

import Hospital_Database.Person.Pacient;

/**
 *
 * @author mistakx
 */

public class MedicMenu {

    static Scanner scannerObject = new Scanner(System.in); // Create a Scanner object

    public static void medicMenuUserInterface() {

        while (true) {

            ClearConsole.clearConsole();
            System.out.println("Escolha a opção que deseja.");
            System.out.println("1 - Listar pacientes em espera no hospital.");
            System.out.println("2 - Listar pacientes a aguardar alta.");
            System.out.println("3 - Diagnóstico ao paciente.");
            System.out.println("4 - Dar alta hospitalar.");
            System.out.println("5 - Requerimento de auxiliares.");
            System.out.println("0 - Voltar ao menu anterior.\n");

            boolean exitMenuUserInterface = false;
            int option;
            option = scannerObject.nextInt();

            switch (option) {

                case 1:
                    listPacientsInHospitalQueue();
                    break;
                case 2:
                    listPacientsAwaitingDischarge();
                    ;
                    break;
                case 3:
                    pacientDiagnostic();
                    break;
                case 4:
                    dischargePacient();
                    break;
                case 5:
                    requisitAuxiliaryNurses();
                    break;
                case 0:
                    exitMenuUserInterface = true;
                    scannerObject.close();
                    break;
                default:
                    System.out.println("Opção inválida\n");
                    // TODO: Add input

            }

            if (exitMenuUserInterface) {
                break;
            }

        }

    }

    public static void listPacientsInHospitalQueue() {
        Queue<Pacient> pacientQueue = Hospital.getPacientQueue();

        for (Pacient pacient : pacientQueue) {
            System.out.println(pacient.getID() + ": " + pacient.getName() + " - " + pacient.getDisease());
        }

    }

    private static void listPacientsAwaitingDischarge() {
        Queue<Pacient> pacientsAwaitingDischargeQueue = Hospital.getPacientsAwaitingDischargeQueue();

        for (Pacient pacient : pacientsAwaitingDischargeQueue){
            System.out.println(pacient.getID() + ": " + pacient.getName());
        } 

    }

    // TODO
    private static void pacientDiagnostic() {

    }

    // TODO
    private static void dischargePacient() { // FIFO = FIRST IN FIRST OUT, Como tem alta vai para o historico do hospital
        Queue<Pacient> pacientsAwaitingDischargeQueue = Hospital.getPacientsAwaitingDischargeQueue();
        pacientsAwaitingDischargeQueue.remove();                        
        
    }

    // TODO
    private static void requisitAuxiliaryNurses() {
        
    }

}
