package Hospital_Database.Person;

public class Nurse extends Person {
    private String name;
    private int careerYears;

    public String getName(){return name;}
    public void setName(String newName){name = newName;}

    public int getCareerYears(){return careerYears;}
    public void setCareerYears(int newCareerYears){careerYears = newCareerYears;}

}