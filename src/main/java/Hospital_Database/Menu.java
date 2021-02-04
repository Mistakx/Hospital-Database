/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital_Database;

import java.util.Scanner;

import Hospital_Database.Exceptions.IDNotFoundException;
import Hospital_Database.Person.Medic;
import Hospital_Database.Person.Nurse;

/**
 *
 * @author mistakx
 */
public class Menu {

    private static Scanner scanner = new Scanner(System.in);
    private static int option;

    public static void mainMenuUserInterface(Hospital hospital) {

        while (true) {

            try {

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
                        System.out.println("A opção inserida é inválida!");
                        scanner.next();
                        break;
                }

            } catch (Exception exception) {
                exception.printStackTrace();
                scanner.next();
            }

        }

    }

    private static void administratorMenuUserInterface(Hospital hospital) {

        while (true) {

            try {

                ClearConsole.clearConsole();
                System.out.println("Escolha a opção que deseja.");
                System.out.println("1 - Criar Médico.");
                System.out.println("2 - Criar Enfeirmeiro-especialista.");
                System.out.println("3 - Criar Enfermeiro-Auxiliar.");
                System.out.println("4 - Criar novo paciente.");
                System.out.println("5 - Promover a enfermeiro-chefe.");
                System.out.println("6 - Aumentar anos de carreira de todos os enfermeiros.");
                System.out.println("7 - Listar enfermeiros.");
                System.out.println("8 - Listar médicos.");
                System.out.println("9 - Listar pedidos para enfermeiros-especialistas.");
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
                        System.out.println("Opção inválida\n");
                        scanner.next();
                        break;

                }

                if (exitMenuUserInterface) {
                    break;
                }
            } catch (Exception exception) {
                System.out.println(exception);
                scanner.next();
            }
        }

    }

    private static void medicMenuUserInterface(Hospital hospital) throws IDNotFoundException {

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

        // If medic with the ID exists
        if (medic != null) {

            while (true) {

                try {

                    ClearConsole.clearConsole();
                    System.out.println("Escolha a opção que deseja.");
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
                            hospital.listPacientsInHospitalQueue();
                            break;
                        case 2:
                            hospital.listPacientsAwaitingDischarge(medic);
                            ;
                            break;
                        case 3:
                            hospital.pacientDiagnostic(medic);
                            break;
                        case 4:
                            hospital.dischargePacient(medic);
                            break;
                        case 5:
                            hospital.requestAuxiliaryNurses(medic);
                            break;
                        case 0:
                            exitMenuUserInterface = true;
                            break;
                        default:
                            System.out.println("Opção inválida\n");
                            scanner.next();
                            break;

                    }

                    if (exitMenuUserInterface) {
                        break;
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                    scanner.next();
                }
            }

        }

        // If medic with the ID doesn't exist
        else {

            throw new IDNotFoundException("Não existe nenhum médico com o ID " + String.valueOf(medicID) + ".");

        }
    }

    private static void nurseMenuUserInterface(Hospital hospital) {

        // Asks the user for ID
        System.out.println("Insira o seu ID: ");
        int nurseID = Integer.parseInt(scanner.next());

        // Checks if a nurse with the ID exists
        Nurse nurse = null;
        // for (Nurse tempNurse : hospital.getMedics()) {

        //     if (tempNurse.getID() == nurseID) {
        //         nurse = tempNurse;
        //     }

        // }

        while (true) {

            try {

                System.out.println("Selecione uma opção!");
                System.out.println("1 - Listar enfermeiros de um médico.");
                System.out.println("2 - Listar pacientes a aguardar curativo.");
                System.out.println("3 - Atribuir enfermeiro-especialista a médico.");
                System.out.println("4 - Aplicar curativo a um paciente.");
                System.out.println("0 - Voltar ao menu anterior.");

                boolean exitMenuUserInterface = false;
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        hospital.listMedicNurses();
                        break;
                    case 2:
                        hospital.listPacientsWaitingForCure();
                        break;

                    case 3:
                        hospital.attributeSpecialistNurseToMedic();
                        break;
                    case 4:
                        hospital.applyCureToPacient();
                        break;
                    case 5:
                        exitMenuUserInterface = true;
                        break;

                    default:
                        System.out.println("Opção Inválida!");
                        scanner.next();
                        break;

                }

                if (exitMenuUserInterface) {
                    break;
                }

            } catch (Exception exception) {
                exception.printStackTrace();
                scanner.next();
            }
        }

    }

}