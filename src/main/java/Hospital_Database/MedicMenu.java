/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital_Database;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import Hospital_Database.Person.ChiefNurse;
import Hospital_Database.Person.Medic;
import Hospital_Database.Person.Person;

/**
 *
 * @author mistakx
 */

public class MedicMenu extends Hospital {

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
        Queue<Person> pacientQueue = Hospital.getPacientQueue();

        for (Person pacient : pacientQueue) {
            System.out.println(pacient.getID() + ": " + pacient.getName() + " - " + pacient.getDisease());
        }

    }

    private static void listPacientsAwaitingDischarge() {
        Queue<Person> pacientsAwaitingDischargeQueue = Hospital.getPacientsAwaitingDischargeQueue();

        for (Person pacient : pacientsAwaitingDischargeQueue) {
            System.out.println(pacient.getID() + ": " + pacient.getName());
        }

    }

    private void pacientDiagnostic() {
        Queue<Person> pacientQueue = Hospital.getPacientQueue();

        for(Person pacient : pacientQueue) {
            int temperature = 20 + (int)(Math.random()*(23));
            double whiteBloodCellLevels = 0.5 + (int)(Math.random()*(101));
            boolean gastrointestinalSymptoms = Math.random() < 0.5;


            System.out.println(pacientQueue.poll() + ".\n");
            System.out.println("Temperatura: " + temperature + "\n");
            System.out.println("Percentagem de leucócitos: " + whiteBloodCellLevels + "%\n");
            System.out.println("Sintomas gastrointestinais: " + gastrointestinalSymptoms);
        }



        


        // int temperature;
        // int whiteBloodCellLevels;
        // boolean gastrointestinalSymptoms;

    }

    // TODO
    private static void dischargePacient() { // FIFO = FIRST IN FIRST OUT, Como tem alta vai para o historico do
                                             // hospital
        Queue<Person> pacientsAwaitingDischargeQueue = Hospital.getPacientsAwaitingDischargeQueue();
        pacientsAwaitingDischargeQueue.remove();

    }

    // TODO
    private static void requisitAuxiliaryNurses() {
        List<Medic> availableMedics = Hospital.getAvailableMedics();
        List<Medic> busyMedics = Hospital.getBusyMedics();

        System.out.println("ID do médico: ");
        int medicID;
        medicID = Integer.parseInt(scannerObject.next());
        boolean medicExists = false;

        for (Medic tempMedic : availableMedics) {
            if(medicID == tempMedic.getID()){
                medicExists = true;
            }
        }

        for (Medic tempMedic : busyMedics){
            if(medicID == tempMedic.getID()){
                medicExists = true;
            }
        }
        
        if (!medicExists){
            System.out.println("Não existe nenhum médico com o ID inserido.");
            return;
        }

        



        System.out.println("ID do chefe enfermeiro: ");
        int chiefNurseID;
        chiefNurseID = Integer.parseInt(scannerObject.next());
        boolean chiefNurseExists = false;

        for (ChiefNurse tempChiefNurse : availableChiefNurses) {
            if(chiefNurseID == tempChiefNurse.getID()){
                chiefNurseExists = true;
            }
        }

        for (Medic tempChiefNurse : busyChiefNurses){
            if(chiefNurseID == tempChiefNurse.getID()){
                chiefNurseExists = true;
            }
        }
        
        if (!chiefNurseExists){
            System.out.println("Não existe nenhum enfermeiro chefe com o ID inserido.");
            return;
        }


        


        System.out.println("Quantos enfermeiros auxiliares necessita: ");
        int auxiliaryNurses;
        auxiliaryNurses = Integer.parseInt(scannerObject.next());
        
        

        
        ChiefNurse test = new ChiefNurse(1, "teste", 1);
        test.
    }

}
