public class Nurse {
    private String name;
    private int careerYears;

    protected String getName(){return name;}
    protected void setName(String newName){name = newName;}

    protected int getCareerYears(){return careerYears;}
    protected void setCareerYears(int newCareerYears){careerYears = newCareerYears;}



}

protected class SpecialistNurse extends Nurse {
    List<int> pacients;


} 

protected class AuxiliaryNurse extends Nurse {


}

protected class ChiefNurse extends Nurse {


}