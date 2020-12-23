package Hospital_Database;

import java.util.List;
import java.util.Scanner;

import Hospital_Database.Person.AuxiliaryNurse;
import Hospital_Database.Person.ChiefNurse;
import Hospital_Database.Person.Medic;
import Hospital_Database.Person.Pacient;
import Hospital_Database.Person.SpecialistNurse;

public class AdministratorMenu {

    static Scanner scannerObject = new Scanner(System.in); // Create a Scanner object

    public static void administratorMenuUserInterface() {
        int option;

        System.out.println("Escolha a opção que deseja.");
        System.out.println("1 - Criar Médico.");
        System.out.println("2 - Criar Enfeirmeiro-especialista");
        System.out.println("3 - Criar Enfermeiro-Auxiliar.");
        System.out.println("4 - Criar novo paciente.");
        System.out.println("5 - Promover a enfermeiro-chefe.");
        System.out.println("6 - Aumentar anos de carreira de todos os enfermeiros.");
        System.out.println("7 - Listar Enfermeiros.");
        System.out.println("8 - Listar médicos.");
        System.out.println("9 -Listar pedidos para enfermeiros-especialistas .");
        System.out.println("10 - Listar pacientes em espera no hospital.");
        System.out.println("11 - Atirar pedidos para enfermeiros-aulixiares para trituradora");
        System.out.println("12 - Virus Outbreak.");
        System.out.println("13 - N-ésimo relatório hospitalar.");
        System.out.println("14 - Sair da aplicação\n");

        do {
            option = scannerObject.nextInt();
            switch (option) {
                case 1:
                    addMedic();
                    break;
                case 2:
                    addSpecialistNurse();
                    break;
                case 3:
                    addAuxiliaryNurse();    // create aux-nurse, just like the jack 3,5mm
                    break;
                case 4:
                    addNewPacient();
                    break;
                case 5:
                    promoteSpecialistNurseToChief();// Promotion to chief Nurse, son a bitch will get raised modafucka
                    break;
                case 6:
                    // Add one more year to all the nurses, get them old
                    break;
                case 7:
                     // List all nurses, showing their Job, exprience year and which medic their
                    // associated
                    break;
                case 8:
                    listMedics();// List medics, nothing else bitch
                    break;
                case 9:
                    listRequestsForAuxiliaryNurses();
                    
                    break;
                case 10:
                    // List all pacientes holding for help, Just like the one on the medic menu
                    break;
                case 11:
                    trashRequestsForAuxiliaryNurses(); // Discard one RANDOM NUMBER of pedidos aos dispor dos medicos
                    break;
                case 12:
                    virusOutbreak();// Virus outbreak, good question how to dos this
                    break;
                case 13:
                     hospitalReports(); // Generate hospital relatory
                    break;
                case 14:
                    // Leave this shit
                    break;

                default:
                    System.out.println("Opção inválida\n");
                    scannerObject.close();

            }
        } while (option >= 1 && option <= 14);

    }

    // TODO: Bernardo: When adding new person, ask if it is already in the system.
    // TODO: For example, if a pacient was a medic.

    public static void addMedic() {
        List<Medic> medics = Hospital.getMedics();

        // ID
        Medic newMedic = new Medic(Hospital.getLastIDAttributed() + 1);
        Hospital.setLastIDAttributed(Hospital.getLastIDAttributed() + 1);

        medics.add(newMedic);
    }

    public static void addSpecialistNurse() {

        List<SpecialistNurse> specialistNurses = Hospital.getSpecialistNurses();
        String specialistNurseName;

        // Name
        System.out.println("Insira o nome do enfermeiro especialista: ");
        specialistNurseName = scannerObject.nextLine();

        // ID
        SpecialistNurse newSpecialistNurse = new SpecialistNurse(Hospital.getLastIDAttributed() + 1,
                specialistNurseName);
        Hospital.setLastIDAttributed(Hospital.getLastIDAttributed() + 1);

        specialistNurses.add(newSpecialistNurse);

    }

    public static void addAuxiliaryNurse() {
        List<AuxiliaryNurse> auxiliaryNurses = Hospital.getAuxiliaryNurses();
        String auxiliaryNurseName;

        // Name
        System.out.println("Insira o nome do enfermeiro auxiliar: ");
        auxiliaryNurseName = scannerObject.nextLine();

        // ID
        AuxiliaryNurse newAuxiliaryNurse = new AuxiliaryNurse(Hospital.getLastIDAttributed(), auxiliaryNurseName);
        Hospital.setLastIDAttributed(Hospital.getLastIDAttributed() + 1);

        auxiliaryNurses.add(newAuxiliaryNurse);

    }

    public static void addNewPacient() {
        List<Pacient> pacients = Hospital.getPacients();

        System.out.println("Ano de nascimento do novo paciente: ");
        String newPacientBirthdayYear = scannerObject.nextLine();
        System.out.println("Doença do paciente: "); // TODO: Change to hard coded disease
        String newPacientDisease = scannerObject.nextLine();
        int newPacientID = pacients.size() + 1;

        Pacient newPacient = new Pacient(newPacientID, newPacientDisease, Integer.parseInt(newPacientBirthdayYear));
        pacients.add(newPacient);
    }

    public static void promoteSpecialistNurseToChief() {
        List<ChiefNurse> chiefNurses = Hospital.getChiefNurses();
        List<SpecialistNurse> specialistNurses = Hospital.getSpecialistNurses();

        int specialistNurseID;
        System.out.println("ID do enfermeiro especialista que deseja promover a chefe: ");
        specialistNurseID = Integer.parseInt(scannerObject.nextLine());

        // If ID exists
        if (specialistNurseID <= Hospital.getLastIDAttributed()) {

            // Check if a specialist nurse with the ID exists
            boolean specialistNurseExists = false;

            for (int i = 0; i < specialistNurses.size(); i++) {

                SpecialistNurse tempSpecialistNurse = specialistNurses.get(i);

                if (tempSpecialistNurse.getID() == specialistNurseID) {
                    specialistNurseExists = true;

                    int nurseCareerYears = tempSpecialistNurse.getCareerYears();
                    String nurseName = tempSpecialistNurse.getName();
                    int nurseID = tempSpecialistNurse.getID();
                    int YEARS_TILL_PROMOTION = Hospital.getYEARS_TILL_PROMOTION();

                    // Promote specialist nurse to chief nurse
                    if (nurseCareerYears >= YEARS_TILL_PROMOTION) {

                        ChiefNurse promotedNurse = new ChiefNurse(nurseID, nurseName, nurseCareerYears);
                        chiefNurses.add(promotedNurse);

                        // TODO: Remove specialist nurse

                    } else {
                        System.out.println("O enfermeiro especialista não tem anos de carreira suficientes.");
                    }

                    break;
                }

            }

            if (!specialistNurseExists) {
                System.out.println("Não existe um enfermeiro especialista com o ID " + specialistNurseID + ".");
            }

        }

        // If ID doesn't exist
        else {
            System.out.println("Não existe nenhuma pessoa com o ID " + specialistNurseID + ".");
        }

    }

    public static void progressNursesCareerYears() {

        List<SpecialistNurse> specialistNurses = Hospital.getSpecialistNurses();
        List<AuxiliaryNurse> auxiliaryNurses = Hospital.getAuxiliaryNurses();
        List<ChiefNurse> chiefNurses = Hospital.getChiefNurses();

        // Specialist nurses
        for (int i = 0; i < specialistNurses.size(); i++) {
            SpecialistNurse tempSpecialistNurse = specialistNurses.get(i);
            tempSpecialistNurse.setCareerYears(tempSpecialistNurse.getCareerYears() + 1);
        }

        // Auxiliary nurses
        for (int i = 0; i < auxiliaryNurses.size(); i++) {
            AuxiliaryNurse tempAuxiliaryNurse = auxiliaryNurses.get(i);
            tempAuxiliaryNurse.setCareerYears(tempAuxiliaryNurse.getCareerYears() + 1);
        }
        // Chief nurses
        for (int i = 0; i < chiefNurses.size(); i++) {
            ChiefNurse tempChiefNurse = chiefNurses.get(i);
            tempChiefNurse.setCareerYears(tempChiefNurse.getCareerYears() + 1);
        }

        System.out.println("Foi adicionado 1 ano de carreira a todos os enfermeiros.");

    }

    public static void listNurses() {
        List<SpecialistNurse> specialistNurses = Hospital.getSpecialistNurses();
        List<AuxiliaryNurse> auxiliaryNurses = Hospital.getAuxiliaryNurses();
        List<ChiefNurse> chiefNurses = Hospital.getChiefNurses();

        // TODO: List what medic the nurse is allocated to

        System.out.println("Enfermeiros especialista");
        // Specialist nurses
        for (int i = 0; i < specialistNurses.size(); i++) {
            SpecialistNurse tempSpecialistNurse = specialistNurses.get(i);
            int nurseCareerYears = tempSpecialistNurse.getCareerYears();
            String nurseName = tempSpecialistNurse.getName();
            int nurseID = tempSpecialistNurse.getID();
            System.out.println(nurseID + ": " + nurseName + " - " + nurseCareerYears);
        }

        System.out.println("Enfermeiros auxiliares");
        // Auxiliary nurses
        for (int i = 0; i < auxiliaryNurses.size(); i++) {
            AuxiliaryNurse tempAuxiliaryNurse = auxiliaryNurses.get(i);
            int nurseCareerYears = tempAuxiliaryNurse.getCareerYears();
            String nurseName = tempAuxiliaryNurse.getName();
            int nurseID = tempAuxiliaryNurse.getID();
            System.out.println(nurseID + ": " + nurseName + " - " + nurseCareerYears);
        }

        System.out.println("Enfermeiros chefe");
        // Chief nurses
        for (int i = 0; i < chiefNurses.size(); i++) {
            ChiefNurse tempChiefNurse = chiefNurses.get(i);
            int nurseCareerYears = tempChiefNurse.getCareerYears();
            String nurseName = tempChiefNurse.getName();
            int nurseID = tempChiefNurse.getID();
            System.out.println(nurseID + ": " + nurseName + " - " + nurseCareerYears);
        }

    }

    public static void listMedics() {
        List<Medic> medics = Hospital.getMedics();
        for (int i = 0; i < medics.size(); i++) {
            Medic tempMedic = medics.get(i);
            System.out.println(tempMedic.getID());
            // TODO: Maybe list each medic associated pacients as well
        }
    }

    public static void listRequestsForAuxiliaryNurses() {
        // TODO
    }

    public static void listPacientsInHospitalQueue() {
        // TODO
    }

    public static void trashRequestsForAuxiliaryNurses() {
        // TODO
    }

    public static void virusOutbreak() {
        // TODO
    }

    public static void hospitalReports() {
        // TODO
    }
}