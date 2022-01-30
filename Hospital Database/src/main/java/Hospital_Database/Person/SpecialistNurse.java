package Hospital_Database.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import Hospital_Database.Hospital;
import Hospital_Database.Menu;
import Hospital_Database.Exceptions.IDNotFoundException;
import Hospital_Database.Exceptions.NoMedicRequestsExistException;
import Hospital_Database.UserInterface.AwaitsUserInput;
import Hospital_Database.UserInterface.ClearConsole;

public class SpecialistNurse extends Nurse implements ChiefNurse {

    // If the specialist nurse is a chief nurse, it can receive requests from a
    // medic for auxiliary nurses
    public HashMap<Medic, Integer> medicRequests;

    // ! Constructor
    public SpecialistNurse(int ID, String name, int birthdayYear, int careerYears) {
        super(ID, name, birthdayYear, careerYears);
        medicRequests = new HashMap<>();
    }

    // ! Getter
    @Override
    public HashMap<Medic, Integer> getMedicAuxiliaryRequests() {
        return medicRequests;
    }

    // ! Overriding the interface methods
    @Override
    public void listMedicAuxiliaryRequests() throws NoMedicRequestsExistException {
        // Prints all medic requests for auxiliares.
        // This method can only be called by a chief nurse.

        ClearConsole.clearConsole();

        // If there no medic requests for auxiliaries, throw an exception
        if (medicRequests.size() == 0) {
            throw new NoMedicRequestsExistException("Não existe nenhum requisito de auxiliares.");
        } // If there medic requests for auxiliaries, print them to the console
        else {

            ArrayList<Medic> medics = new ArrayList<>(medicRequests.keySet());

            System.out.println("Pedidos de enfermeiros auxiliares\n");

            for (Medic medic : medics) {
                System.out.println(
                        medic.toString() + "\nNúmero de pedidos: " + String.valueOf(medicRequests.get(medic)) + "\n");
            }

        }

        // Waits for user input
        AwaitsUserInput.awaitsUserInput();

    }

    @Override
    public void fulfilMedicAuxiliaryRequest(Hospital hospital) throws NoMedicRequestsExistException {
        // Fulfils a medics request for auxiliary nurses.
        // This method can only be called by a chief nurse.

        ClearConsole.clearConsole();

        // Input medic ID to fulfil request
        System.out.println("ID do médico que requisitou auxiliares: ");
        int medicID = Menu.scanner.nextInt();

        Queue<Medic> medicsAwaitingRequests = new LinkedList<>(medicRequests.keySet());

        Medic medic = null;
        for (Medic tempMedic : medicsAwaitingRequests) {
            if (tempMedic.getID() == medicID) {
                medic = tempMedic;
            }
        }

        // If medic doesn't exist, throw an exception
        if (medic == null) {
            throw new NoMedicRequestsExistException(
                    "Não existe nenhum médico com o ID introduzido à espera de auxiliares.");
        } // If medic exists, fulfils his requests
        else {
            int numberOfRequestedAuxiliaries = medicRequests.get(medic);
            int numberOfAuxiliariesAttributed = 0;

            for (AuxiliaryNurse tempAuxiliaryNurse : hospital.getAuxiliaryNurses()) {

                // If the associated nurse doesn't have an associated medic already, attributes
                // it to the medic
                if (tempAuxiliaryNurse.getAssociatedMedic() == null) {
                    medic.getAuxiliaryNurses().add(tempAuxiliaryNurse);
                    tempAuxiliaryNurse.setAssociatedMedic(medic);
                    numberOfAuxiliariesAttributed++;

                    if (numberOfAuxiliariesAttributed == numberOfRequestedAuxiliaries) {
                        break;
                    }

                }
            }

            System.out.println("Auxiliares atribuídos");
            AwaitsUserInput.awaitsUserInput();

        }

    }

    @Override
    public void attributeSpecialistNurseToMedic(Hospital hospital) throws IDNotFoundException { //
        // Attributes a specialist nurse to a medic. This method can only be called by a
        // chief nurse.

        ClearConsole.clearConsole();

        // ! Asks the user for the specialist nurse to attribute to a medic
        System.out.println("ID do enfermeiro especialista a atribuir: ");
        int specialistNurseID = Menu.scanner.nextInt();

        SpecialistNurse specialistNurse = null;

        // Check if a specialist nurse with the ID exists
        for (SpecialistNurse tempSpecialistNurse : hospital.getSpecialistNurses()) {

            if (tempSpecialistNurse.getID() == specialistNurseID) {
                specialistNurse = tempSpecialistNurse;
                break;
            }

        }

        // If the specialist nurse doesn't exist, throw an exception
        if (specialistNurse == null) {
            throw new IDNotFoundException("Não existe um enfermeiro especialista com o ID " + specialistNurseID + ".");
        } // ! If the specialist nurse exists, asks the user for Medic and checks if it
        // exists
        else {
            ClearConsole.clearConsole();
            System.out.println("ID do médico: ");
            int medicID = Menu.scanner.nextInt();

            // Check if a medic with the ID exists
            Medic medic = null;
            for (Medic tempMedic : hospital.getMedics()) {

                if (tempMedic.getID() == medicID) {
                    medic = tempMedic;
                    break;
                }

            }

            // If the medic doesn't exist, throws an exception
            if (medic == null) {
                throw new IDNotFoundException("Não existe um médico com o ID " + medicID + ".");
            } // If the medic exists, attribute the specialist to the found medic
            else {
                specialistNurse.setAssociatedMedic(medic);
                medic.getSpecialistNurses().add(specialistNurse);
                ClearConsole.clearConsole();
                System.out.println("Enfermeiro especialista atribuído.");
                Menu.scanner.next();
            }

        }

    }

    // ! Overriding object methods
    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
