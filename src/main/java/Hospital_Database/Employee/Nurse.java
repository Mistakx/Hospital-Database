package Hospital_Database.Employee;

public class Nurse extends Employee {
    private String name;
    private int careerYears;

    protected String getName(){return name;}
    protected void setName(String newName){name = newName;}

    protected int getCareerYears(){return careerYears;}
    protected void setCareerYears(int newCareerYears){careerYears = newCareerYears;}

}