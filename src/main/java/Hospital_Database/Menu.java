/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital_Database;

import Hospital_Database.UserInterface.ClearConsole;
import java.util.Scanner;

import Hospital_Database.Exceptions.IDNotFoundException;
import Hospital_Database.Exceptions.NotEnoughPermissionsException;
import Hospital_Database.Person.AuxiliaryNurse;
import Hospital_Database.Person.ChiefNurse;
import Hospital_Database.Person.Medic;
import Hospital_Database.Person.Nurse;
import Hospital_Database.Person.SpecialistNurse;

/**
 *
 * @author mistakx
 */
public class Menu {

    public static Scanner scanner = new Scanner(System.in);
    private static int option;

    public static void mainMenuUserInterface(Hospital hospital) {
        // Prints the main menu user interface to the console

        while (true) {

            try {
                scanner.reset();
                ClearConsole.clearConsole();
                System.out.println("Selecione o menu que deseja:");
                System.out.println("1 - Menu Médico");
                System.out.println("2 - Menu Enfermeiro");
                System.out.println("3 - Menu Administrador\n");

                option = scanner.nextInt();

                switch (option) {

                    case 1:
                        medicMenuUserInterface(hospital);
                        break;
                    case 2:
                        nurseMenuUserInterface(hospital);
                        break;
                    case 3:
                        administratorMenuUserInterface(hospital);
                        break;

                    default:
                        ClearConsole.clearConsole();
                        System.out.println("Opção inválida.\n");
                        scanner.next();
                        break;
                }

            } catch (IDNotFoundException exception) {
                scanner.reset();
                ClearConsole.clearConsole();
                System.out.println(exception.getMessage());
                scanner.next();

            }

        }

    }

    private static void administratorMenuUserInterface(Hospital hospital) {
        // Prints administrator menu user interface to the console

        while (true) {

            try {

                ClearConsole.clearConsole();
                System.out.println("Selecione uma opção.");
                System.out.println("1 - Criar Médico.");
                System.out.println("2 - Criar Enfeirmeiro-especialista.");
                System.out.println("3 - Criar Enfermeiro-Auxiliar.");
                System.out.println("4 - Criar novo paciente.");
                System.out.println("5 - Promover a enfermeiro-chefe.");
                System.out.println("6 - Aumentar anos de carreira de todos os enfermeiros.");
                System.out.println("7 - Listar enfermeiros.");
                System.out.println("8 - Listar médicos.");
                System.out.println("9 - Listar pedidos para enfermeiros-auxiliares, feitos ao hospital.");
                System.out.println("10 - Listar pacientes em espera no hospital.");
                System.out.println("11 - Atirar pedidos para enfermeiros-aulixiares para trituradora.");
                System.out.println("12 - Virus Outbreak.");
                System.out.println("13 - N-ésimo relatório hospitalar.");
                System.out.println("14 - Sair do programa");
                System.out.println("0 - Voltar ao menu anterior.\n");

                boolean exitMenuUserInterface = false;
                int option;
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        hospital.addMedic();
                        break;
                    case 2:
                        hospital.addSpecialistNurse();
                        break;
                    case 3:
                        hospital.addAuxiliaryNurse();
                        break;
                    case 4:
                        hospital.addNewPacient();
                        break;
                    case 5:
                        hospital.promoteSpecialistNurseToChief();
                        break;
                    case 6:
                        hospital.progressNursesCareerYears();
                        break;
                    case 7:
                        hospital.listNurses();
                        break;
                    case 8:
                        hospital.listMedics();
                        break;
                    case 9:
                        hospital.listRequestsForAuxiliaryNurses();

                        break;
                    case 10:
                        hospital.listPacientsInHospitalQueue();

                        break;
                    case 11:
                        hospital.trashRequestsForAuxiliaryNurses();
                        break;
                    case 12:
                        hospital.virusOutbreak();
                        break;
                    case 13:
                        hospital.hospitalReports();
                        break;
                    case 14:
                        System.exit(0);
                        break;

                    case 0:
                        exitMenuUserInterface = true;
                        break;

                    default:
                        ClearConsole.clearConsole();
                        System.out.println("Opção inválida.\n");
                        scanner.next();
                        break;

                }

                if (exitMenuUserInterface) {
                    break;
                }
            } catch (Exception exception) {
                scanner.reset();
                ClearConsole.clearConsole();
                System.out.println(exception.getMessage());
                scanner.next();
            }
        }

    }

    private static void medicMenuUserInterface(Hospital hospital) throws IDNotFoundException {
        // Prints medic menu user interface to the console

        ClearConsole.clearConsole();

        // Asks the user for ID
        System.out.println("Insira o seu ID: ");
        int medicID = Integer.parseInt(scanner.next());

        // Checks if a medic with the ID exists
        Medic medic = null;
        for (Medic tempMedic : hospital.getMedics()) {

            if (tempMedic.getID() == medicID) {
                medic = tempMedic;
            }

        }

        // If medic with the ID doesn't exist
        if (medic == null) {

            throw new IDNotFoundException("Não existe nenhum médico com o ID " + String.valueOf(medicID) + ".");

        }

        // If medic with the ID exists
        else {

            while (true) {

                try {

                    ClearConsole.clearConsole();
                    System.out.println("Selecione uma opção.");
                    System.out.println("1 - Listar pacientes em espera no hospital.");
                    System.out.println("2 - Listar pacientes a aguardar alta.");
                    System.out.println("3 - Diagnóstico ao paciente.");
                    System.out.println("4 - Dar alta hospitalar.");
                    System.out.println("5 - Requerimento de auxiliares.");
                    System.out.println("0 - Voltar ao menu anterior.\n");

                    boolean exitMenuUserInterface = false;
                    option = scanner.nextInt();

                    switch (option) {

                        case 1:
                            medic.listPacientsInHospitalQueue(hospital);
                            break;
                        case 2:
                            medic.listPacientsAwaitingDischarge();
                            ;
                            break;
                        case 3:
                            medic.pacientDiagnostic(hospital);
                            break;
                        case 4:
                            medic.dischargePacient();
                            break;
                        case 5:
                            medic.requestAuxiliaryNurses(hospital);
                            break;
                        case 0:
                            exitMenuUserInterface = true;
                            break;
                        default:
                            ClearConsole.clearConsole();
                            System.out.println("Opção inválida.\n");
                            scanner.next();
                            break;

                    }

                    if (exitMenuUserInterface) {
                        break;
                    }

                } catch (Exception exception) {
                    scanner.reset();
                    ClearConsole.clearConsole();
                    System.out.println(exception.getMessage());
                    scanner.next();
                }
            }

        }

    }

    private static void nurseMenuUserInterface(Hospital hospital) throws IDNotFoundException {
        // Prints nurse menu user interface to the console

        ClearConsole.clearConsole();

        // Asks the user for ID
        System.out.println("Insira o seu ID: ");
        int nurseID = Integer.parseInt(scanner.next());

        // Search for a nurse with the ID in all the nurses lists
        Nurse nurse = null;
        boolean nurseIsChief = false;
        for (AuxiliaryNurse tempAuxiliaryNurse : hospital.getAuxiliaryNurses()) {
            if (tempAuxiliaryNurse.getID() == nurseID) {
                nurse = tempAuxiliaryNurse;
                break;
            }
        }
        for (SpecialistNurse tempSpecialistNurse : hospital.getSpecialistNurses()) {
            if (tempSpecialistNurse.getID() == nurseID) {
                nurse = tempSpecialistNurse;
                break;
            }
        }
        for (SpecialistNurse tempChiefNurse : hospital.getChiefNurses()) {
            if (tempChiefNurse.getID() == nurseID) {
                nurse = tempChiefNurse;
                nurseIsChief = true;
                break;
            }
        }

        // If the nurse doesn't exist, throw an exception
        if (nurse == null) {
            throw new IDNotFoundException("Não existe nenhum enfermeiro com o ID inserido.");
        }

        while (true) {

            try {
                ClearConsole.clearConsole();
                System.out.println("Selecione uma opção.");
                System.out.println("1 - Listar enfermeiros de um médico.");
                System.out.println("2 - Listar pacientes a aguardar curativo.");
                System.out.println("3 - Atribuir enfermeiro-especialista a médico.");
                System.out.println("4 - Aplicar curativo a um paciente.");
                System.out.println("5 - Listar requisitos de enfermeiros auxiliares.");
                System.out.println("6 - Atender aos requisitos de enfermeiros auxiliares.");
                System.out.println("0 - Voltar ao menu anterior.");

                boolean exitMenuUserInterface = false;
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        nurse.listMedicNurses(hospital);
                        break;
                    case 2:
                        nurse.listPacientsWaitingForCure();
                        break;

                    case 3:
                        if (nurseIsChief) {
                            ((ChiefNurse) nurse).attributeSpecialistNurseToMedic(hospital);

                        } else {
                            throw new NotEnoughPermissionsException(
                                    "Apenas um enfermeiro chefe pode atribuír um enfermeiro especialista.");

                        }
                        break;
                    case 4:
                        nurse.applyCureToPacient(hospital);

                        break;
                    case 5:
                        if (nurseIsChief) {

                            ((ChiefNurse) nurse).listMedicAuxiliaryRequests();
                        } else {
                            throw new NotEnoughPermissionsException(
                                    "Apenas um enfermeiro chefe pode ver os pedidos por auxiliares.");
                        }
                    case 6:
                        if (nurseIsChief) {
                            ((ChiefNurse) nurse).fulfilMedicAuxiliaryRequest(hospital);
                        } else {
                            throw new NotEnoughPermissionsException(
                                    "Apenas um enfermeiro chefe pode atender ao pedido de enfermeiros auxiliares.");
                        }
                    case 0:
                        exitMenuUserInterface = true;
                        break;

                    default:
                        ClearConsole.clearConsole();
                        System.out.println("Opção inválida.\n");
                        scanner.next();
                        break;

                }

                if (exitMenuUserInterface) {
                    break;
                }

            } catch (Exception exception) {
                scanner.reset();
                ClearConsole.clearConsole();
                System.out.println(exception.getMessage());
                scanner.next();

            }
        }

    }

}