import nurse.ChiefNurse;
import nurse.SpecialistNurse;

public class administratorMenu {
    final private int YEARS_TILL_PROMOTION = 20;

    public void addMedic(){
        // TODO
    }
    public void addSpecialistNurse(){
        // TODO
    }
    public void addAuxiliaryNurse(){
        // TODO
    }
    public void addPacient(){
        // TODO
    }
    public ChiefNurse promoteSpecialistNurse(SpecialistNurse nurse){
        // TODO: Test

        int careerYears = nurse.getCareerYears();

        if (careerYears >= YEARS_TILL_PROMOTION) {
            ChiefNurse promotedNurse = new ChiefNurse();
            promotedNurse.setCareerYears(careerYears);
            promotedNurse.setName(nurse.getName());
            System.out.println("O enfermeiro não têm anos de carreira suficientes.");
        }

        return promoteSpecialistNurse(nurse);
    }


}

