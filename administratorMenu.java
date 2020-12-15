import nurse.java*;

public static class administratorMenu {
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
    public SpecialistNurse promoteSpecialistNurse(SpecialistNurse nurse){
        // TODO

        int careerYears = nurse.getCareerYears()

        if (careerYears >= YEARS_TILL_PROMOTION) {
            SpecialistNurse promotedNurse;
            promotedNurse.setCareerYears(careerYears);
            promotedNurse.setName(nurse.getName);
            System.out.prinln("O enfermeiro não têm anos de carreira suficientes.")
        }
    }


}

