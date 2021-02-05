package Hospital_Database.Person;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import Hospital_Database.Hospital;
import Hospital_Database.Exceptions.IDNotFoundException;
import Hospital_Database.Exceptions.NoMedicRequestsExistException;
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

    @Override
    public HashMap<Medic, Integer> getMedicAuxiliaryRequests() {
        return medicRequests;
    }

    // ! Overriding the interface methods

    @Override
    public void listMedicAuxiliaryRequests() throws NoMedicRequestsExistException { // Prints all medic requests for
                                                                                    // auxiliares. This method can only
                                                                                    // be called by a chief nurse.

        ClearConsole.clearConsole();

        // If there no medic requests for auxiliaries, throw an exception

        if (medicRequests.size() == 0) {
            throw new NoMedicRequestsExistException("Não existe nenhum requisito de auxiliares.");
        }

        // If there medic requests for auxiliaries, print them to the console
        else {
            System.out.println(medicRequests.toString());
        }

    }

    @Override
    public void fulfilMedicAuxiliaryRequest(Hospital hospital) throws IDNotFoundException { // Fulfils a medics request
                                                                                            // for auxiliary nurses.
                                                                                            // This
        // method can only
        // be called by a chief nurse.

        // Input medic ID to fulfil request
        Scanner scanner = new Scanner(System.in);
        int medicID = scanner.nextInt();

        Queue<Medic> medicsAwaitingRequests = new LinkedList<>(medicRequests.keySet());

        Medic medic = null;
        for (Medic tempMedic : medicsAwaitingRequests) {
            if (tempMedic.getID() == medicID) {
                medic = tempMedic;
            }
        }

        // If medic doesn't exist, throw an exception
        if (medic == null) {
            scanner.close();
            throw new IDNotFoundException("Não existe nenhum médico com o ID introduzido.");
        }

        // If medic exists, fulfils his requests
        else {
            for (AuxiliaryNurse tempAuxiliaryNurse : hospital.getAuxiliaryNurses()) {
                // If the associated nurse doesn't have an associated medic already, attributes
                // it to the medic
                if (tempAuxiliaryNurse.getAssociatedMedic() == null) {
                    medic.getAuxiliaryNurses().add(tempAuxiliaryNurse);
                }
            }
        }
        scanner.close();

    }

    // Done
    @Override
    public void attributeSpecialistNurseToMedic(Hospital hospital) throws IDNotFoundException { //
        // Attributes a specialist nurse to a medic. This method can only be called by a
        // chief nurse.

        ClearConsole.clearConsole();

        // ! Asks the user for the specialist nurse to attribute to a medic
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID do enfermeiro especialista a atribuir: ");
        int specialistNurseID = scanner.nextInt();

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
            scanner.close();
            throw new IDNotFoundException("Não existe um enfermeiro especialista com o ID " + specialistNurseID + ".");
        }

        // ! If the specialist nurse exists, asks the user for Medic and checks if it
        // exists
        else {

            System.out.println("ID do médico: ");
            int medicID = scanner.nextInt();

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
                scanner.close();
                throw new IDNotFoundException("Não existe um médico com o ID " + medicID + ".");
            }

            // If the medic exists, attribute the specialist to the found medic
            else {
                medic.getSpecialistNurses().add(specialistNurse);
            }

        }

        scanner.close();

    }

}
