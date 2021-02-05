package Hospital_Database.Person;

import java.util.HashMap;

import Hospital_Database.ClearConsole;
import Hospital_Database.Exceptions.NoMedicRequestsExistException;

public class SpecialistNurse extends Nurse implements ChiefNurse {

    // If the specialist nurse is a chief nurse, it can receive requests from a
    // medic for auxiliary nurses
    public HashMap<Medic, Integer> medicRequests = new HashMap<>();

    public SpecialistNurse(int ID, String name, int birthdayYear, int careerYears) {
        super(ID, name, birthdayYear, careerYears);
    }

    @Override
    public HashMap<Medic, Integer> getMedicRequests() {
        return medicRequests;
    }

    @Override
    public void listMedicRequests() throws NoMedicRequestsExistException { // Prints all medic requests for auxiliares
                                                                           // to the console

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
    public void fulfilMedicRequest() { // Fulfils a medics request for auxiliary nurses
        // TODO Auto-generated method stub

    }

}
